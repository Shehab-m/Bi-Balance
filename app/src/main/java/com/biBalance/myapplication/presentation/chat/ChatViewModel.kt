package com.biBalance.myapplication.presentation.chat

import android.util.Log
import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.ChatBotResponse
import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: BiBalanceRepository
) : BaseViewModel<ChatUIState, ChatUIEffect>(ChatUIState()), ChatInteractionListener {

    init {
        tryToExecute(
            { repository.sendChat("اهلا") },
            ::onGetChatResponse,
            ::onError
        )
    }

    override fun onWritingsInputChange(text: String) {
        _state.update { it.copy(writing = text) }
    }

    private fun getChatResponse() {
        updateState { it.copy(isChatLoading = true) }
        Log.d("getChatResponse3:", _state.value.writing)
        tryToExecute(
            { repository.sendChat(_state.value.writing) },
            ::onGetChatResponse,
            ::onError
        )
    }

    private fun onGetChatResponse(chat: ChatBotResponse) {
        updateState { it.copy(chatResponse = chat, isLoading = false,isChatLoading = false, writing = "") }
    }

    private fun onError(error: Exception) {
        _state.update {
            it.copy(
                isLoading = false,
                isChatLoading = false,
                isError = true,
                error = error,
            )
        }
    }

    override fun onClickSend() {
        getChatResponse()
    }

}