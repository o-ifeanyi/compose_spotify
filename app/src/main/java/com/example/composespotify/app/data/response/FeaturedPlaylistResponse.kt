package com.example.composespotify.app.data.response

import com.example.composespotify.app.data.model.PaginatedData
import com.example.composespotify.app.data.model.PlaylistModel


data class FeaturedPlaylistResponse(
    val message: String,
    val playlists: PaginatedData<PlaylistModel>
)