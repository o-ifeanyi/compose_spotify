package com.example.composespotify.app.domain.repository

import com.example.composespotify.core.resource.Resource

interface AuthRepository {
    suspend fun getAndSetToken(): Resource<Boolean>
}