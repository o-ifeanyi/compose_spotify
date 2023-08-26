package com.example.composespotify.app.data.model

import android.util.Log
import com.example.composespotify.app.domain.entity.DetailEntity
import com.example.composespotify.app.domain.entity.TrackDataEntity
import com.example.composespotify.app.domain.entity.FeedType
import com.example.composespotify.app.domain.entity.HomeFeedData
import com.google.gson.annotations.SerializedName

data class PlaylistModel(
    val collaborative: Boolean,
    val description: String,
    @SerializedName(value = "external_urls")
    val externalUrls: ExternalUrlsModel,
    val href: String,
    val id: String,
    val images: List<ImageModel>,
    val name: String,
    val owner: OwnerModel,
    val public: Boolean,
    @SerializedName(value = "snapshot_id")
    val snapshotId: String,
    val tracks: PaginatedData<PlaylistTrackModel>?,
    val type: String,
    val uri: String
)

fun PlaylistModel.toDetailEntity(): DetailEntity {
    val trackData = tracks?.let { tracks ->
        tracks.items?.map {
            try {
                TrackDataEntity(
                    id = it.track.id,
                    url = it.track.album.images.last().url,
                    title = it.track.name,
                    subtitle = it.track.artists.first().name,
                    previewUrl = it.track.previewUrl ?: ""
                )
            } catch (e: Exception) {
                Log.d("toDetailEntity failure", e.toString())
                null
            }
        }
    } ?: emptyList()

    var time = 0
    for (i in (tracks?.items ?: emptyList())) {
        time += i.track.durationMs
    }

    return DetailEntity(
        id = id,
        url = images.first().url,
        name = name,
        description = description,
        duration = "$time",
        tracks = trackData.filterNotNull()
    )
}

fun PlaylistModel.toCategoryPlaylist(): HomeFeedData? {
    return try {
        HomeFeedData(
            id = id,
            type = FeedType.Playlist,
            url = images.first().url,
            header = description,
            subtitle = ""
        )
    } catch (e: Exception) {
        Log.d("toFeaturedPlaylist failure", e.toString())
        null
    }
}