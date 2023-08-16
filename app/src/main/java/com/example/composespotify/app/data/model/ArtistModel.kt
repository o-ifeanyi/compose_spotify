package com.example.composespotify.app.data.model

import com.google.gson.annotations.SerializedName

data class ArtistModel(
    @SerializedName(value = "external_urls")
    val externalUrls: ExternalUrlsModel,
    val followers: FollowersModel,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<ImageModel>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String
)