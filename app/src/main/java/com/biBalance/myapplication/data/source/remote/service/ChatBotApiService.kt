package com.biBalance.myapplication.data.source.remote.service

import com.biBalance.myapplication.data.source.remote.model.ChatBotResponse
import com.biBalance.myapplication.data.source.remote.model.ChatRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatBotApiService {
    @POST("chat")
    suspend fun sendChat(
        @Body body: ChatRequest
    ): Response<ChatBotResponse>
}

