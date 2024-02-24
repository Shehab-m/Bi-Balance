package com.biBalance.myapplication.presentation.activity

import com.biBalance.myapplication.presentation.base.BaseUiEffect

sealed class ActivityUIEffect: BaseUiEffect {
    object OnClickBack: ActivityUIEffect()

}
