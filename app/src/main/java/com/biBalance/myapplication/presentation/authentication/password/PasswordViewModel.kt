package com.biBalance.myapplication.presentation.authentication.password

import android.util.Log
import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.domain.useCase.ValidateAuthFieldsUseCase
import com.biBalance.myapplication.presentation.authentication.login.FieldState
import com.biBalance.myapplication.presentation.base.BaseViewModel
import com.biBalance.myapplication.util.StringDictionary
import com.biBalance.myapplication.util.ValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor(
    private val stringResource: StringDictionary,
    private val repository: BiBalanceRepository,
    private val validationUseCase: ValidateAuthFieldsUseCase
) : BaseViewModel<PasswordUIState, PasswordUIEffect>(PasswordUIState()),
    PasswordInteractionListener {

    override fun onClickBack() {
        sendEffect(PasswordUIEffect.OnClickBack)
    }

    override fun onOldPasswordInputChanged(password: CharSequence) {
        _state.update {
            it.copy(oldPasswordState = FieldState(value = password.toString(), errorMessage = ""))
        }
    }

    override fun onNewPasswordInputChanged(password: CharSequence) {
        _state.update {
            it.copy(newPasswordState = FieldState(value = password.toString(), errorMessage = ""))
        }
    }

    private fun changePassword(
        oldPassword: String,
        newPassword: String,
        newPasswordConfirmation: String
    ) {
        _state.update { it.copy(isButtonLoading = true) }
        tryToExecute(
            {
                repository.changePassword(
                    newPassword = newPassword,
                    oldPassword = oldPassword,
                    newPasswordConfirmation = newPasswordConfirmation
                )
            },
            ::onChangePasswordSuccess,
            ::onError,
        )
    }

    private fun onChangePasswordSuccess(unit: Unit) {
        sendEffect(PasswordUIEffect.ClickChangePasswordEffect)
    }

    override fun onConfirmationPasswordInputChanged(password: CharSequence) {
        Log.d(
            "onConfirmationPasswordInputChanged: ",
            "$password ${_state.value.newPasswordState.value}"
        )
        _state.update {
            it.copy(
                confirmationPasswordState = FieldState(
                    value = password.toString(),
                    errorMessage = if (password != _state.value.newPasswordState.value) "password doesn't match" else "",
                ),
            )
        }
        _state.update { it.copy(isButtonEnabled = password.toString() == _state.value.newPasswordState.value) }
    }

    private fun onError(error: Exception) {
        _state.update {
            it.copy(
                isButtonLoading = false,
                isButtonEnabled = true,
                oldPasswordState = _state.value.oldPasswordState.copy(errorMessage = " "),
                newPasswordState = _state.value.newPasswordState.copy(errorMessage = " "),
                confirmationPasswordState = _state.value.confirmationPasswordState.copy(errorMessage = " ")
            )
        }
        showValidationToast(message = error.message ?: "Unknown error")
    }

    override fun onClickChangePassword() {
        _state.update { it.copy(isButtonEnabled = false) }
        val oldPasswordFieldValidation =
            validationUseCase.validationPassword(_state.value.oldPasswordState.value)
        val newPasswordFieldValidation =
            validationUseCase.validationPassword(_state.value.newPasswordState.value)
        val renewPasswordFieldValidation =
            validationUseCase.validationPassword(_state.value.confirmationPasswordState.value)
        if (oldPasswordFieldValidation == ValidationState.VALID_PASSWORD
            && newPasswordFieldValidation == ValidationState.VALID_PASSWORD
            && renewPasswordFieldValidation == ValidationState.VALID_PASSWORD
        ) {
            _state.update {
                it.copy(
                    oldPasswordState = state.value.oldPasswordState.copy(errorMessage = ""),
                    newPasswordState = state.value.newPasswordState.copy(errorMessage = ""),
                    confirmationPasswordState = state.value.confirmationPasswordState.copy(
                        errorMessage = ""
                    ),
                    isButtonEnabled = false,
                    isButtonLoading = true
                )
            }
            changePassword(
                _state.value.oldPasswordState.value,
                _state.value.newPasswordState.value,
                _state.value.confirmationPasswordState.value,
            )
        } else {
            _state.update { it.copy(isButtonEnabled = true, isButtonLoading = false) }
            showValidationToast(stringResource.requiredFieldsMessageString)
        }
    }

    private fun showValidationToast(message: String) {
        _state.update {
            it.copy(
                validationToast = state.value.validationToast.copy(
                    isShow = true,
                    message = message
                ),
                isButtonLoading = false
            )
        }
        sendEffect(PasswordUIEffect.ShowToastEffect)
    }

}