package com.biBalance.myapplication.data.repository

import com.biBalance.myapplication.data.source.remote.model.ActivitiesScores
import com.biBalance.myapplication.data.source.remote.model.Activity
import com.biBalance.myapplication.data.source.remote.model.ChatBotResponse
import com.biBalance.myapplication.data.source.remote.model.Level
import com.biBalance.myapplication.data.source.remote.model.LevelActivities
import com.biBalance.myapplication.data.source.remote.model.Note
import com.biBalance.myapplication.data.source.remote.model.Tasks
import com.biBalance.myapplication.data.source.remote.model.UserData
import com.biBalance.myapplication.data.source.remote.model.UserPost

interface BiBalanceRepository {
    suspend fun loginUser(email: String, password: String)
    suspend fun logoutUser()
    suspend fun storeResult(id: Int, score: Int)
    suspend fun getAccessToken(): String?
    suspend fun clearToken()
    suspend fun getHomeLevels(): List<Level>
    suspend fun getUserData(): UserData
    suspend fun getLevelActivities(id: Int): LevelActivities
    suspend fun getActivity(id: Int): Activity
    suspend fun getActivit(id: Int): String
    suspend fun getActivitiesScores(): ActivitiesScores
    suspend fun getNotes(): List<Note>
    suspend fun saveNotes(notes: String)
    suspend fun savePost(post: String)
    suspend fun changePassword(
        oldPassword: String, newPassword: String, newPasswordConfirmation: String
    )
    suspend fun storeTasks(
        taskOne: String,
        taskTwo: String,
        taskThree: String,
        taskFour: String
    )
    suspend fun getTasksForDate(date: String): Tasks?
    suspend fun getArticlePosts(): List<UserPost>?
    suspend fun likeUserPost(id: Int)
    suspend fun unlikeUserPost(id: Int)
    // ChatBot
    suspend fun sendChat(chat: String): ChatBotResponse
}