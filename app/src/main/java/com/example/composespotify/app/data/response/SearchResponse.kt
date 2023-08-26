package com.example.composespotify.app.data.response

import android.util.Log
import com.example.composespotify.app.data.model.*
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

    val artists = artists.items?.map { artist ->
        try {
            TrackDataEntity(
                id = artist.id,
                url = artist.images.first().url,
                title = artist.name,
                subtitle = "",
                previewUrl = ""
            )
        } catch (e: Exception) {
            Log.d("toSearchEntity artist failure", e.toString())
            null
        }
    } ?: emptyList()

    val albums = albums.items?.map { album ->
        try {
            TrackDataEntity(
                id = album.id,
                url = album.images.first().url,
                title = album.name,
                subtitle = album.artists.first().name,
                previewUrl = ""
            )
        } catch (e: Exception) {
            Log.d("toSearchEntity album failure", e.toString())
            null
        }
    } ?: emptyList()

    val playlists = playlists.items?.map { playlist ->
        try {
            TrackDataEntity(
                id = playlist.id,
                url = playlist.images.first().url,
                title = playlist.name,
                subtitle = playlist.description,
                previewUrl = ""
            )
        } catch (e: Exception) {
            Log.d("toSearchEntity album failure", e.toString())
            null
        }
    } ?: emptyList()

    return SearchEntity(
        tracks = tracks.filterNotNull(),
        artists = artists.filterNotNull(),
        albums = albums.filterNotNull(),
        playlists = playlists.filterNotNull()
    )
}