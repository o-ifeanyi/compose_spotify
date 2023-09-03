package com.example.composespotify.app.data.response

import android.util.Log
import com.example.composespotify.app.data.model.*
import com.example.composespotify.app.domain.entity.FeedType
import com.example.composespotify.app.domain.entity.HomeFeedData
import com.example.composespotify.app.domain.entity.SearchEntity
import com.example.composespotify.app.domain.entity.TrackDataEntity

data class SearchResponse(
    val tracks: PaginatedData<TrackObjectModel>,
    val artists: PaginatedData<ArtistModel>,
    val albums: PaginatedData<AlbumModel>,
    val playlists: PaginatedData<PlaylistModel>
)

fun SearchResponse.toSearchEntity(): SearchEntity {
    val tracks = tracks.items?.map { track ->
        try {
            TrackDataEntity(
                id = track.id,
                url = track.album.images.first().url,
                title = track.name,
                subtitle = track.artists.first().name,
                previewUrl = track.previewUrl ?: ""
            )
        } catch (e: Exception) {
            Log.d("toSearchEntity tracks failure", e.toString())
            null
        }
    } ?: emptyList()

    val albums = albums.items?.map { album ->
        try {
            HomeFeedData(
                id = album.id,
                type = FeedType.Album,
                url = album.images.first().url,
                header = album.name,
                subtitle = album.artists.first().name,
            )
        } catch (e: Exception) {
            Log.d("toSearchEntity album failure", e.toString())
            null
        }
    } ?: emptyList()

    val playlists = playlists.items?.map { playlist ->
        try {
            HomeFeedData(
                id = playlist.id,
                type = FeedType.Playlist,
                url = playlist.images.first().url,
                header = playlist.name,
                subtitle = playlist.description,
            )
        } catch (e: Exception) {
            Log.d("toSearchEntity album failure", e.toString())
            null
        }
    } ?: emptyList()

    return SearchEntity(
        tracks = tracks.filterNotNull(),
        albums = albums.filterNotNull(),
        playlists = playlists.filterNotNull()
    )
}