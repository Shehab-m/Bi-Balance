package com.biBalance.myapplication.presentation.writings

import com.biBalance.myapplication.presentation.base.BaseUiEffect

sealed class WritingsUIEffect: BaseUiEffect {
    object OnClickBack: WritingsUIEffect()
}
