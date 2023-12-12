package com.biBalance.myapplication.presentation.home

import com.biBalance.myapplication.presentation.base.BaseUiEffect

sealed class HomeUIEffect: BaseUiEffect {
    data class OnClickLevel(val id:Int): HomeUIEffect()
}
