package com.biBalance.myapplication.presentation.profile

import com.biBalance.myapplication.presentation.base.BaseUiEffect

sealed class ProfileUIEffect: BaseUiEffect {
    object OnClickProfileImage: ProfileUIEffect()
}
