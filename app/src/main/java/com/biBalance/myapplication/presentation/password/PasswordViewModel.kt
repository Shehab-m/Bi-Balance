package com.biBalance.myapplication.presentation.password

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

    init {
        getUserData()
    }

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

    override fun onRenewPasswordInputChanged(password: CharSequence) {
        _state.update {
            it.copy(
                renewPasswordState = FieldState(
                    value = password.toString(),
                    errorMessage = if (password != _state.value.newPasswordState.value) "password doesn't match" else ""
                )
            )
        }
    }

    override fun onClickChangePassword() {
        _state.update { it.copy(isButtonEnabled = false, isLoading = true) }
        val oldPasswordFieldValidation =
            validationUseCase.validationPassword(_state.value.oldPasswordState.value)
        val newPasswordFieldValidation =
            validationUseCase.validationPassword(_state.value.newPasswordState.value)
        val renewPasswordFieldValidation =
            validationUseCase.validationPassword(_state.value.renewPasswordState.value)
        if (oldPasswordFieldValidation == ValidationState.VALID_PASSWORD
            && newPasswordFieldValidation == ValidationState.VALID_PASSWORD
            && renewPasswordFieldValidation == ValidationState.VALID_PASSWORD
        ) {
            Log.d("onClickChangePassword: ",renewPasswordFieldValidation.toString())
            _state.update {
                it.copy(
                    oldPasswordState = state.value.oldPasswordState.copy(errorMessage = ""),
                    newPasswordState = state.value.newPasswordState.copy(errorMessage = ""),
                    renewPasswordState = state.value.renewPasswordState.copy(errorMessage = "")
                )
            }
        } else {
            _state.update { it.copy(isButtonEnabled = true, isLoading = false) }
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
                isLoading = false
            )
        }
        sendEffect(PasswordUIEffect.ShowToastEffect)
    }
    private fun getUserData() {
    }


}