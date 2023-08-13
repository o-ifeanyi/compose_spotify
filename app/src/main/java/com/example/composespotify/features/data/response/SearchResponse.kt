package com.example.composespotify.features.data.response

import com.example.composespotify.features.data.model.*

data class SearchResponse(
    val tracks: PaginatedData<TrackObjectModel>,
    val artists: PaginatedData<ArtistModel>,
    val albums: PaginatedData<AlbumModel>,
    val playlists: PaginatedData<PlaylistModel>
)
