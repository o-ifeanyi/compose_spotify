package com.example.composespotify.app.data.model

import com.google.gson.annotations.SerializedName

data class NewReleasesModel(
    val album_group: String,
    val album_type: String,
    val artists: List<ArtistModel>,
    @SerializedName(value = "available_markets")
    val availableMarkets: List<String>,
    val copyrights: List<CopyrightModel>,
    @SerializedName(value = "external_ids")
    val externalIds: ExternalIdsModel,
    @SerializedName(value = "external_urls")
    val externalUrls: ExternalUrlsModel,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<ImageModel>,
    val label: String,
    val name: String,
    val popularity: Int,
    @SerializedName(value = "release_date")
    val releaseDate: String,
    @SerializedName(value = "release_date_precision")
    val releaseDatePrecision: String,
    val restrictions: RestrictionsModel,
    @SerializedName(value = "total_tracks")
    val totalTracks: Int,
    val type: String,
    val uri: String
)