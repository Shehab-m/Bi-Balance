package com.biBalance.myapplication.data.source.remote

import android.util.Log
import com.biBalance.myapplication.data.source.local.AuthPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authPreferences: AuthPreferences,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken = authPreferences.storedAccessToken.takeUnless { it.isNullOrEmpty() }
            ?: authPreferences.getAccessToken()
        Log.d("Uer Token: ", "$accessToken")
        return makeRequest(chain, accessToken)
    }

    private fun makeRequest(
        chain: Interceptor.Chain,
        accessToken: String?
    ): Response {
        val oldRequest = chain
            .request()
            .newBuilder()
            .addHeader(AUTHORIZATION, "Bearer $accessToken")
            .addHeader(API_KEY, API_KEY_VALUE)
            .build()

        return chain.proceed(oldRequest)
    }


    companion object {
        private const val API_KEY = "Accept"
        private const val API_KEY_VALUE = "application/json"
        private const val AUTHORIZATION = "Authorization"
        const val BASE_URL = "http://10.0.2.2:8000/api/"
        const val CHAT_BASE_URL = "http://192.168.1.6:5000/api/"
    }
}