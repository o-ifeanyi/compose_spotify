package com.example.composespotify.app.data.model

import com.google.gson.annotations.SerializedName

data class OwnerModel(
    @SerializedName(value = "display_name")
    val displayName: String,
    @SerializedName(value = "external_urls")
    val externalUrls: ExternalUrlsModel,
    val followers: FollowersModel,
    val href: String,
    val id: String,
    val type: String,
    val uri: String
)