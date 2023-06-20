package com.example.composespotify.features.data.response

import com.example.composespotify.features.data.model.PaginatedData
import com.example.composespotify.features.data.model.PlaylistModel


data class FeaturedPlaylistResponse(
    val message: String,
    val playlists: PaginatedData<PlaylistModel>
)