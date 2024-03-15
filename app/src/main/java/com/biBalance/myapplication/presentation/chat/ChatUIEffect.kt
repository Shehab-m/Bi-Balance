package com.biBalance.myapplication.presentation.chat

import com.biBalance.myapplication.presentation.base.BaseUiEffect

sealed class ChatUIEffect: BaseUiEffect {
    data class OnClickLevel(val id:Int): ChatUIEffect()
}
