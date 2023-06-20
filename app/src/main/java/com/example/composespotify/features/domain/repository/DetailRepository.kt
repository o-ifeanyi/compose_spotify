package com.example.composespotify.features.domain.repository

import com.example.composespotify.core.resource.Resource
import com.example.composespotify.features.data.model.AlbumModel
import com.example.composespotify.features.data.model.PlaylistModel

interface DetailRepository {
    suspend fun getAlbum(id: String): Resource<AlbumModel>
    suspend fun getPlaylist(id: String): Resource<PlaylistModel>
}