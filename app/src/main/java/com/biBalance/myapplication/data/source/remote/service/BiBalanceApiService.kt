package com.biBalance.myapplication.data.source.remote.service

import com.biBalance.myapplication.data.source.remote.model.ActivitiesData
import com.biBalance.myapplication.data.source.remote.model.Activity
import com.biBalance.myapplication.data.source.remote.model.BaseResponse
import com.biBalance.myapplication.data.source.remote.model.Level
import com.biBalance.myapplication.data.source.remote.model.LevelActivity
import com.biBalance.myapplication.data.source.remote.model.LoginResponse
import com.biBalance.myapplication.data.source.remote.model.Note
import com.biBalance.myapplication.data.source.remote.model.TaskCreated
import com.biBalance.myapplication.data.source.remote.model.UserData
import com.biBalance.myapplication.data.source.remote.model.UserPost
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BiBalanceApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String, @Field("password") password: String
    ): Response<BaseResponse<LoginResponse>>

    @POST("logout")
    suspend fun logoutUser(): Response<BaseResponse<LoginResponse>>

    @FormUrlEncoded
    @POST("storeResult/{id}")
    suspend fun storeResult(
        @Path("id") id: Int, @Field("score") score: Int
    ): Response<BaseResponse<Any?>>

    @GET("showLevelActivities/{id}")
    suspend fun getLevelActivities(
        @Path("id") id: Int
    ): Response<BaseResponse<List<LevelActivity>>>

    @GET("showActivity/{id}")
    suspend fun getActivity(
        @Path("id") id: Int
    ): Response<BaseResponse<Activity>>

    @GET("showActivity/{id}")
    suspend fun getActivit(
        @Path("id") id: Int
    ): Response<String>
    @GET("homepage")
    suspend fun getHomeLevels(): Response<BaseResponse<List<Level>>>

    @GET("getUserData")
    suspend fun getUserData(): Response<BaseResponse<UserData>>

    @GET("type")
    suspend fun getActivitiesScores(): Response<BaseResponse<ActivitiesData>>

    @GET("notes")
    suspend fun getNotes(): Response<BaseResponse<List<Note>>>

    @FormUrlEncoded
    @POST("notes")
    suspend fun saveNotes(
        @Field("body") notes: String
    ): Response<BaseResponse<Any>>

    @FormUrlEncoded
    @POST("post")
    suspend fun savePost(
        @Field("body") post: String
    ): Response<BaseResponse<Any>>

    @GET("tasks/{date}")
    suspend fun getTasksForDate(@Path("date") date: String): Response<BaseResponse<List<TaskCreated>?>>

    @FormUrlEncoded
    @POST("changePassword")
    suspend fun changePassword(
        @Field("oldPassword") oldPassword: String,
        @Field("newPassword") newPassword: String,
        @Field("newPassword_confirmation") newPasswordConfirmation: String
    ): Response<BaseResponse<Any>>

    @FormUrlEncoded
    @POST("tasks")
    suspend fun storeTasks(
        @Field("task1") taskOne: String,
        @Field("task2") taskTwo: String,
        @Field("task3") taskThree: String,
        @Field("task4") taskFour: String
    ): Response<BaseResponse<Any>>

    @GET("posts")
    suspend fun getUserPosts(): Response<BaseResponse<List<UserPost>?>>

    @POST("like/{id}")
    suspend fun likeUserPost(
        @Path("id") id: Int
    ): Response<BaseResponse<Any?>>

    @POST("unlike/{id}")
    suspend fun unlikeUserPost(
        @Path("id") id: Int
    ): Response<BaseResponse<Any?>>
}