package com.example.composespotify.app.data.response

import com.example.composespotify.app.data.model.*

data class SearchResponse(
    val tracks: PaginatedData<TrackObjectModel>,
    val artists: PaginatedData<ArtistModel>,
    val albums: PaginatedData<AlbumModel>,
    val playlists: PaginatedData<PlaylistModel>
)
