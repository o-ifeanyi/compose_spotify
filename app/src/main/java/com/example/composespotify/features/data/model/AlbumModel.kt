package com.example.composespotify.features.data.model

import android.util.Log
import com.example.composespotify.features.domain.entity.DetailEntity
import com.example.composespotify.features.domain.entity.DetailTrackData
import com.google.gson.annotations.SerializedName

data class AlbumModel(
    @SerializedName(value = "album_group")
    val albumGroup: String,
    @SerializedName(value = "album_type")
    val albumType: String,
    val artists: List<ArtistModel>,
    @SerializedName(value = "available_markets")
    val availableMarkets: List<String>,
    @SerializedName(value = "external_urls")
    val externalUrls: ExternalUrlsModel,
    val href: String,
    val id: String,
    val images: List<ImageModel>,
    val name: String,
    @SerializedName(value = "release_date")
    val releaseDate: String,
    @SerializedName(value = "release_date_precision")
    val releaseDatePrecision: String,
    val label: String?,
    @SerializedName(value = "total_tracks")
    val totalTracks: Int,
    val tracks: PaginatedData<AlbumTracksModel>?,
    val type: String,
    val uri: String
)

fun AlbumModel.toDetailEntity(): DetailEntity {
    val trackData = tracks?.let {
        it.items.map { track ->
            try {
                DetailTrackData(
                    id = track.id,
                    url = "",
                    title = track.name,
                    artist = track.artists.first().name,
                    previewUrl = track.previewUrl ?: ""
                )
            } catch (e: Exception) {
                Log.d("toDetailEntity failure", e.toString())
                null
            }
        }
    } ?: emptyList()

    var time = 0
    for (i in (tracks?.items ?: emptyList())) {
        time += i.durationMs
    }

    return DetailEntity(
        id = id,
        url = images.first().url,
        name = artists.first().name,
        description = label ?: "",
        duration = "${time}",
        tracks = trackData.filterNotNull()
    )
}