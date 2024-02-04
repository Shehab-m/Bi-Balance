package com.biBalance.myapplication.presentation.authentication.login

import com.biBalance.myapplication.presentation.base.BaseUiEffect


sealed interface LoginUiEffect: BaseUiEffect {
    object ClickSignUpEffect : LoginUiEffect
    object ClickLoginEffect : LoginUiEffect
    object ShowToastEffect : LoginUiEffect
}