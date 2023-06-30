package com.example.composespotify.features.data.model

import android.util.Log
import com.example.composespotify.features.domain.entity.FeedType
import com.example.composespotify.features.domain.entity.HomeFeedData
import com.example.composespotify.features.domain.entity.HomeFeedEntity

data class PaginatedData<T>(
    val items: List<T>?,
    val href: String?,
    val next: String?,
    val previous: String?,
    val offset: Int?,
    val limit: Int?,
    val total: Int?
)

fun PaginatedData<PlaylistModel>.toFeaturedPlaylist(): HomeFeedEntity {
    val data: List<PlaylistModel> = items ?: emptyList()
    val homeFeedData = data.map {
        try {
            HomeFeedData(
                id = it.id,
                type = FeedType.Playlist,
                url = it.images.first().url,
                header = it.description,
                subtitle = ""
            )
        } catch (e: Exception) {
            Log.d("toFeaturedPlaylist failure", e.toString())
            null
        }
    }
    return HomeFeedEntity(
        id = "featured_playlist",
        title = "Featured Playlist",
        data = homeFeedData.filterNotNull()
    )
}

fun PaginatedData<NewReleasesModel>.toNewReleases(): HomeFeedEntity {
    val data: List<NewReleasesModel> = items ?: emptyList()
    val homeFeedData = data.map {
        try {
            HomeFeedData(
                id = it.id,
                type = FeedType.Album,
                url = it.images.first().url,
                header = it.name,
                subtitle = it.artists.first().name
            )
        } catch (e: Exception) {
            Log.d("toFeaturedPlaylist failure", e.toString())
            null
        }
    }
    return HomeFeedEntity(
        id = "new_releases",
        title = "New Releases",
        data = homeFeedData.filterNotNull()
    )
}