package com.biBalance.myapplication.presentation.profile

import com.biBalance.myapplication.presentation.base.BaseUiEffect

sealed class ProfileUIEffect: BaseUiEffect {
    object OnClickLogout: ProfileUIEffect()
    object OnClickActivities: ProfileUIEffect()
    object OnClickWritings: ProfileUIEffect()
    object OnClickTodo: ProfileUIEffect()
    object OnClickPassword: ProfileUIEffect()
    object OnClickArticles: ProfileUIEffect()
}
