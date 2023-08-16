package com.example.composespotify.app.data.model

import com.google.gson.annotations.SerializedName

data class AlbumTracksModel(
    val artists: List<ArtistModel>,
    @SerializedName(value = "available_markets")
    val availableMarkets: List<String>,
    @SerializedName(value = "disc_number")
    val discNumber: Int,
    @SerializedName(value = "duration_ms")
    val durationMs: Int,
    val explicit: Boolean,
    @SerializedName(value = "external_urls")
    val externalUrls: ExternalUrlsModel,
    val href: String,
    val id: String,
    @SerializedName(value = "is_local")
    val isLocal: Boolean,
    @SerializedName(value = "is_playable")
    val isPlayable: Boolean,
    val name: String,
    @SerializedName(value = "preview_url")
    val previewUrl: String?,
    val restrictions: RestrictionsModel,
    @SerializedName(value = "track_number")
    val trackNumber: Int,
    val type: String,
    val uri: String
)