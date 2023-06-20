package com.example.composespotify.features.data.model

import com.google.gson.annotations.SerializedName

data class RecommendationsModel(
    val album: AlbumModel,
    val artists: List<ArtistModel>,
    @SerializedName(value = "available_markets")
    val availableMarkets: List<String>,
    @SerializedName(value = "disc_number")
    val discNumber: Int,
    @SerializedName(value = "duration_ms")
    val durationMs: Int,
    val explicit: Boolean,
    @SerializedName(value = "external_ids")
    val externalIds: ExternalIdsModel,
    @SerializedName(value = "external_urls")
    val external_urls: ExternalUrlsModel,
    val href: String,
    val id: String,
    @SerializedName(value = "is_local")
    val isLocal: Boolean,
    val name: String,
    val popularity: Int,
    @SerializedName(value = "preview_url")
    val previewUrl: String,
    @SerializedName(value = "track_number")
    val trackNumber: Int,
    val type: String,
    val uri: String
)