package com.example.composespotify.features.domain.repository

import com.example.composespotify.core.resource.Resource

interface AuthRepository {
    suspend fun getAndSetToken(): Resource<Boolean>
}