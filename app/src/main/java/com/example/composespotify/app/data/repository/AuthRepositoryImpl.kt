package com.example.composespotify.app.data.repository

import android.util.Log
import com.example.composespotify.core.service.ApiService
import com.example.composespotify.core.resource.Resource
import com.example.composespotify.core.service.DataStoreService
import com.example.composespotify.app.domain.repository.AuthRepository
import java.time.LocalDateTime
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val apiService: ApiService, private val dataStoreService: DataStoreService) :
    AuthRepository {
    override suspend fun getAndSetToken(): Resource<Boolean> {
        return try {
            val res = apiService.getToken()
            Log.d("getToken success", res.toString())
            dataStoreService.saveToken(res.accessToken)
            val expiresIn = LocalDateTime.now().plusSeconds(res.expiresIn.toLong())
            dataStoreService.saveTokenExp(expiresIn)
            Resource.Success(data = true)
        } catch (e: Exception) {
            Log.d("getToken failure", e.stackTraceToString())
            Resource.Error(message = e.message ?: "Something went wrong")
        }
    }
}