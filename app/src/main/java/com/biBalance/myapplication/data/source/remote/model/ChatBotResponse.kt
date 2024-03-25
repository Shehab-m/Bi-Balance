package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class ChatBotResponse(
    @SerializedName("bot_response")
    val botResponse: String,
    @SerializedName("convo_history")
    val chatHistory: List<ChatLog>,
    @SerializedName("user_input")
    val userInput: String,
) {
    data class ChatLog(
        @SerializedName("role")
        val role: String,
        @SerializedName("text")
        val text: String,
    )
}

data class ChatRequest(
    @SerializedName("user_input") val userInput: String
)