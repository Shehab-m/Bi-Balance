package com.biBalance.myapplication.data.repository

import android.util.Log
import com.biBalance.myapplication.data.source.local.AuthPreferences
import com.biBalance.myapplication.data.source.remote.model.ActivitiesScores
import com.biBalance.myapplication.data.source.remote.model.Activity
import com.biBalance.myapplication.data.source.remote.model.BaseResponse
import com.biBalance.myapplication.data.source.remote.model.ChatBotResponse
import com.biBalance.myapplication.data.source.remote.model.ChatRequest
import com.biBalance.myapplication.data.source.remote.model.Level
import com.biBalance.myapplication.data.source.remote.model.LevelActivities
import com.biBalance.myapplication.data.source.remote.model.Note
import com.biBalance.myapplication.data.source.remote.model.Tasks
import com.biBalance.myapplication.data.source.remote.model.UserData
import com.biBalance.myapplication.data.source.remote.model.UserPost
import com.biBalance.myapplication.data.source.remote.service.BiBalanceApiService
import com.biBalance.myapplication.data.source.remote.service.ChatBotApiService
import retrofit2.Response
import retrofit2.http.Field
import javax.inject.Inject

class BiBalanceRepositoryImp @Inject constructor(
    private val biBalanceService: BiBalanceApiService,
    private val chatBotService: ChatBotApiService,
    private val datastore: AuthPreferences,
) : BiBalanceRepository {

    override suspend fun loginUser(email: String, password: String) {
        val response = wrap { biBalanceService.loginUser(email = email, password = password) }.data
        datastore.saveTokens(response.token)
    }

    override suspend fun logoutUser() {
        wrap { biBalanceService.logoutUser() }.data
        datastore.clearToken()
    }

    override suspend fun storeResult(id: Int, score: Int) {
        wrap { biBalanceService.storeResult(id = id, score = score) }.data
    }

    override suspend fun likeUserPost(id: Int) {
        wrap { biBalanceService.likeUserPost(id = id) }.data
    }

    override suspend fun unlikeUserPost(id: Int) {
        wrap { biBalanceService.unlikeUserPost(id = id) }.data
    }

    override suspend fun getHomeLevels(): List<Level> {
        return wrap { biBalanceService.getHomeLevels() }.data
    }

    override suspend fun getLevelActivities(id: Int): LevelActivities {
        return wrap { biBalanceService.getLevelActivities(id) }.data
    }

    override suspend fun getActivity(id: Int): Activity {
        val result = wrap { biBalanceService.getActivity(id = id) }.data
        Log.d("getActivity: ", result.toString())
        return result
    }

    override suspend fun getActivit(id: Int): String {
        val result = biBalanceService.getActivit(id = id).body()!!
        Log.d("getActivity: ", result.toString())
        return result
    }

    override suspend fun getUserData(): UserData {
        return wrap { biBalanceService.getUserData() }.data
    }

    override suspend fun getActivitiesScores(): ActivitiesScores {
        return wrap { biBalanceService.getActivitiesScores() }.data.activitiesScores
    }

    override suspend fun getNotes(): List<Note> {
        return wrap { biBalanceService.getNotes() }.data.notes
    }

    override suspend fun saveNotes(notes: String) {
        wrap { biBalanceService.saveNotes(notes) }.data
    }

    override suspend fun savePost(post: String) {
        wrap { biBalanceService.savePost(post) }.data
    }

    override suspend fun changePassword(
        oldPassword: String,
        newPassword: String,
        newPasswordConfirmation: String
    ) {
        wrap {
            biBalanceService.changePassword(
                oldPassword,
                newPassword,
                newPasswordConfirmation
            )
        }.data
    }


    override suspend fun storeTasks(
        taskOne: String,
        taskTwo: String,
        taskThree: String,
        taskFour: String
    ) {
        wrap { biBalanceService.storeTasks(taskOne, taskTwo, taskThree, taskFour) }.data
    }


    override suspend fun getTasksForDate(date: String): Tasks? {
        return wrap { biBalanceService.getTasksForDate(date) }.data
    }

    override suspend fun sendChat(chat: String): ChatBotResponse {
        return chatBotService.sendChat(ChatRequest(chat)).body()!!
    }

    override suspend fun getUserPosts(): List<UserPost>? {
        return wrap { biBalanceService.getUserPosts() }.data
    }

    override suspend fun getAccessToken(): String? {
        return datastore.getAccessToken()
    }

    override suspend fun clearToken() {
        return datastore.clearToken()
    }

    private suspend fun <T> wrap(function: suspend () -> Response<BaseResponse<T>>): BaseResponse<T> {
        try {
            val response = function().body()!!
            return if (response.success) {
                Log.d("Tag", "repository done correctly: ${response.data}")
                response
            } else {
                Log.d("Tag", "repository failed :${response.message.errorMessage}")
                throw Exception("${response.message.errorMessage}")
            }
        } catch (e: Exception) {
            Log.e("Tag", "response Error:${e.message}")
            throw Exception("${e.message}")
        }
    }
}