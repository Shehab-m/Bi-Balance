package com.biBalance.myapplication.presentation.authentication.login

import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.domain.useCase.ValidateAuthFieldsUseCase
import com.biBalance.myapplication.presentation.base.BaseViewModel
import com.biBalance.myapplication.util.ErrorHandler
import com.biBalance.myapplication.util.StringDictionary
import com.biBalance.myapplication.util.ValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val stringResource: StringDictionary,
    private val repository: BiBalanceRepository,
    private val validationUseCase: ValidateAuthFieldsUseCase
) : BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState()), LoginInteractionListener {

    init {
//        checkIfUserLoggedIn()
        sendEffect(LoginUiEffect.ClickLoginEffect)
        sendEffect(LoginUiEffect.ClickSignUpEffect)
    }


    override fun onClickSignup() {

    }

    override fun onClickLogin() {
        _state.update { it.copy(isButtonEnabled = false) }
        val emailFieldValidation =
            validationUseCase.validateEmail(_state.value.emailState.value)
        val passwordFieldValidation =
            validationUseCase.validationPassword(_state.value.passwordState.value)
        if (emailFieldValidation == ValidationState.VALID_EMAIL
            && passwordFieldValidation == ValidationState.VALID_PASSWORD
        ) {
            _state.update {
                it.copy(
                    emailState = state.value.emailState.copy(errorMessage = ""),
                    passwordState = state.value.passwordState.copy(errorMessage = "")
                )
            }
            loginUser(
                email = state.value.emailState.value,
                password = state.value.passwordState.value
            )
        } else {
            _state.update { it.copy(isButtonEnabled = true) }
            showValidationToast(stringResource.requiredFieldsMessageString)
        }
    }

    private fun loginUser(email: String, password: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { repository.loginUser(email = email, password = password) },
            ::onLoginSuccess,
            ::onError,
        )
    }

    private fun onLoginSuccess(unit: Unit) {
        _state.update { it.copy(isError = false, error = null) }
        sendEffect(LoginUiEffect.ClickLoginEffect)
    }

    private fun onError(error: ErrorHandler) {
        val errorMessage = stringResource.errorString.getOrDefault(error, "")
        _state.update {
            it.copy(
                isLoading = false,
                isError = true,
                error = error,
                isButtonEnabled = true,
                emailState = _state.value.emailState.copy(errorMessage = " "),
                passwordState = _state.value.passwordState.copy(errorMessage = " ")
            )
        }
        showValidationToast(message = errorMessage)
    }


    override fun onEmailInputChange(email: CharSequence) {
        _state.update {
            it.copy(emailState = FieldState(value = email.toString(), errorMessage = ""))
        }
    }

    override fun onPasswordInputChanged(password: CharSequence) {
        _state.update {
            it.copy(passwordState = FieldState(value = password.toString(), errorMessage = ""))
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
        sendEffect(LoginUiEffect.ShowToastEffect)
    }

//    fun checkIfUserLoggedIn() {
//        viewModelScope.launch {
//            if (repository.getAccessToken() != null) {
//                Log.d("checkIfUserLoggedIn: ", "true")
//                _effect.emit(LoginUiEffect.ClickLoginEffect)
//                Log.d("checkIfUserLoggedIn: ", repository.getAccessToken()!!)
//            } else {
//                Log.d("checkIfUserLoggedIn: ", "false")
//                updateState { it.copy(isLoading = false) }
//            }
//        }
//
//    }
}

//package com.biBalance.myapplication.presentation.authentication.login
//
//import android.util.Log
//import com.biBalance.myapplication.data.repository.BiBalanceRepository
//import com.biBalance.myapplication.domain.useCase.ValidateAuthFieldsUseCase
//import com.biBalance.myapplication.presentation.base.BaseViewModel
//import com.biBalance.myapplication.util.ErrorHandler
//import com.biBalance.myapplication.util.StringDictionary
//import com.biBalance.myapplication.util.ValidationState
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.update
//import javax.inject.Inject
//
//@HiltViewModel
//class LoginViewModel @Inject constructor(
//    private val stringResource: StringDictionary,
//    private val repository: BiBalanceRepository,
//    private val validationUseCase: ValidateAuthFieldsUseCase
//) : BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState()), LoginInteractionListener {
//
////    init {
//////        checkIfUserLoggedIn()
////        Log.d("checkIfUserLoggedIn: ", "init")
////        sendEffect(LoginUiEffect.ClickSignUpEffect)
////        viewModelScope.launch {
////            _effect.emit(LoginUiEffect.ClickLoginEffect)
////
////        }
////    }
//
//    override fun onClickSignup() {
//
//    }
//
//    override fun onClickLogin() {
//        _state.update { it.copy(isButtonEnabled = false) }
//        val emailFieldValidation =
//            validationUseCase.validateEmail(_state.value.emailState.value)
//        val passwordFieldValidation =
//            validationUseCase.validationPassword(_state.value.passwordState.value)
//        if (emailFieldValidation == ValidationState.VALID_EMAIL
//            && passwordFieldValidation == ValidationState.VALID_PASSWORD
//        ) {
//            _state.update {
//                it.copy(
//                    emailState = state.value.emailState.copy(errorMessage = ""),
//                    passwordState = state.value.passwordState.copy(errorMessage = "")
//                )
//            }
//            loginUser(
//                email = state.value.emailState.value,
//                password = state.value.passwordState.value
//            )
//        } else {
//            _state.update { it.copy(isButtonEnabled = true) }
//            showValidationToast(stringResource.requiredFieldsMessageString)
//        }
//    }
//
//    private fun loginUser(email: String, password: String) {
//        _state.update { it.copy(isLoading = true) }
//        tryToExecute(
//            { repository.loginUser(email = email, password = password) },
//            ::onLoginSuccess,
//            ::onLoginError,
//        )
//    }
//
//    private fun onLoginSuccess(unit: Unit) {
//        _state.update { it.copy(isError = false, error = null) }
//        sendEffect(LoginUiEffect.ClickLoginEffect)
//    }
//
//    private fun onLoginError(error: ErrorHandler) {
//        val errorMessage = stringResource.errorString.getOrDefault(error, "")
//        _state.update {
//            it.copy(
//                isLoading = false,
//                isError = true,
//                error = error,
//                isButtonEnabled = true,
//                emailState = _state.value.emailState.copy(errorMessage = " "),
//                passwordState = _state.value.passwordState.copy(errorMessage = " ")
//            )
//        }
//        showValidationToast(message = errorMessage)
//    }
//
//
//    override fun onEmailInputChange(email: CharSequence) {
//        _state.update {
//            it.copy(emailState = FieldState(value = email.toString(), errorMessage = ""))
//        }
//    }
//
//    override fun onPasswordInputChanged(password: CharSequence) {
//        _state.update {
//            it.copy(passwordState = FieldState(value = password.toString(), errorMessage = ""))
//        }
//    }
//
//    private fun showValidationToast(message: String) {
//        _state.update {
//            it.copy(
//                validationToast = state.value.validationToast.copy(
//                    isShow = true,
//                    message = message
//                ),
//                isLoading = false
//            )
//        }
//        sendEffect(LoginUiEffect.ShowToastEffect)
//    }
//
//    fun checkIfUserLoggedIn() {
//        tryToExecute(
//            { repository.getAccessToken() },
//            ::onCheckSuccess,
//            ::onError,
//        )
//    }
//    private fun onCheckSuccess(token: String?) {
//        if (token != null) {
//            Log.d("checkIfUserLoggedIn: ", "onCheckSuccess")
//            sendEffect(LoginUiEffect.ClickLoginEffect)
//            _effect.emit(LoginUiEffect.ClickLoginEffect)
//        }
//    }
//    private fun onError(error: ErrorHandler) {
//        _state.update {
//            it.copy(
//                isLoading = false,
//                isError = true,
//                error = error,
//                isButtonEnabled = true,
//                emailState = _state.value.emailState.copy(errorMessage = " "),
//                passwordState = _state.value.passwordState.copy(errorMessage = " ")
//            )
//        }
//    }
//
//}