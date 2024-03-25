package com.biBalance.myapplication.presentation.authentication.password

import com.biBalance.myapplication.presentation.authentication.login.FieldState
import com.biBalance.myapplication.presentation.authentication.login.ValidationToast

data class PasswordUIState(
    val oldPasswordState: FieldState = FieldState(),
    val newPasswordState: FieldState = FieldState(),
    val confirmationPasswordState: FieldState = FieldState(),
    val isButtonEnabled: Boolean = true,
    val isButtonLoading: Boolean = false,
    val validationToast: ValidationToast = ValidationToast(),
)