package com.biBalance.myapplication.presentation.authentication.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val error: Exception? = null,
    val emailState: FieldState = FieldState(),
    val passwordState: FieldState = FieldState(),
    val isButtonEnabled: Boolean = true,
    val validationToast: ValidationToast = ValidationToast(),
)

data class FieldState(
    val value: String = "",
    val errorMessage: String = "",
    val isValid: Boolean = errorMessage.isNotEmpty()
)

data class ValidationToast(
    val isShow: Boolean = false,
    val message: String = "Please fill all required fields"
)

