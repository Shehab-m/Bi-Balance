package com.biBalance.myapplication.presentation.chat

import com.biBalance.myapplication.data.source.remote.model.ChatBotResponse

data class ChatUIState(
    val isLoading: Boolean = true,
    val isChatLoading: Boolean = false,
    val isError: Boolean = false,
    val error: Exception? = null,
    val writing: String = "",
    val chatResponse: ChatBotResponse = ChatBotResponse("", emptyList(),""),
)