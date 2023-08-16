package com.example.composespotify.core.di

import com.example.composespotify.core.service.DataStoreService
import com.example.composespotify.core.util.Constants
import com.example.composespotify.core.util.Secrets
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
class RequestInterceptor @Inject constructor(private val dataStoreService: DataStoreService) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return runBlocking {
            val isTokenRequest = chain.request().headers["isTokenRequest"]
            if (!isTokenRequest.isNullOrEmpty()) {
                val token =
                    Base64.encode("${Secrets.CLIENT_ID}:${Secrets.CLIENT_SECRET}".toByteArray())
                val request = chain.request().newBuilder()
                    .url(Constants.AUTH_URL)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Authorization", "Basic $token")
                    .removeHeader("isTokenRequest")
                    .build()
                chain.proceed(request)
            } else {
                val token = dataStoreService.getAccessToken()

                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }
        }
    }
}