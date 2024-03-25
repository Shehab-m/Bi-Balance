package com.biBalance.myapplication.presentation.authentication.password

import com.biBalance.myapplication.presentation.base.BaseUiEffect

sealed class PasswordUIEffect: BaseUiEffect {
    object OnClickBack: PasswordUIEffect()
    object ShowToastEffect: PasswordUIEffect()
    object ClickChangePasswordEffect: PasswordUIEffect()
}
