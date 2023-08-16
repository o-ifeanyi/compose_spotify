package com.example.composespotify.app.data.model

import com.google.gson.annotations.SerializedName

data class CredentialModel(
    @SerializedName(value = "access_token")
    val accessToken: String,
    @SerializedName(value = "expires_in")
    val expiresIn: Int,
    @SerializedName(value = "token_type")
    val tokenType: String
)