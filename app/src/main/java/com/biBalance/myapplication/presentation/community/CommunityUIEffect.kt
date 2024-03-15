package com.biBalance.myapplication.presentation.community

import com.biBalance.myapplication.presentation.base.BaseUiEffect

sealed class CommunityUIEffect: BaseUiEffect {
    data class OnClickLevel(val id:Int): CommunityUIEffect()
}
