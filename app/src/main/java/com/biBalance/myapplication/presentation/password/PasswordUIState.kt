package com.biBalance.myapplication.presentation.password

import com.biBalance.myapplication.presentation.authentication.login.FieldState
import com.biBalance.myapplication.presentation.authentication.login.ValidationToast

data class PasswordUIState(
    val isLoading: Boolean = false,
    val oldPasswordState: FieldState = FieldState(),
    val newPasswordState: FieldState = FieldState(),
    val renewPasswordState: FieldState = FieldState(),
    val isButtonEnabled: Boolean = true,
    val validationToast: ValidationToast = ValidationToast(),
)