package com.biBalance.myapplication.di

import com.biBalance.myapplication.data.source.local.AuthPreferences
import com.biBalance.myapplication.data.source.remote.AuthInterceptor
import com.biBalance.myapplication.data.source.remote.service.BiBalanceApiService
import com.biBalance.myapplication.data.source.remote.service.ChatBotApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMainRetrofitService(
        client: OkHttpClient,
        factory: GsonConverterFactory
    ): BiBalanceApiService {
        return Retrofit.Builder()
            .baseUrl(AuthInterceptor.BASE_URL)
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(BiBalanceApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideChatBotRetrofitService(
        client: OkHttpClient,
        factory: GsonConverterFactory
    ): ChatBotApiService {
        return Retrofit.Builder()
            .baseUrl(AuthInterceptor.CHAT_BASE_URL)
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(ChatBotApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Singleton
    @Provides
    fun provideGson(): GsonConverterFactory =
        GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setPrettyPrinting()
                .create()
        )

    @Singleton
    @Provides
    fun provideHeaderInterceptor(dataStorePreferences: AuthPreferences): AuthInterceptor {
        return AuthInterceptor(dataStorePreferences)
    }
}