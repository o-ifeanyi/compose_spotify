package com.example.composespotify.app.domain.repository

import com.example.composespotify.core.resource.Resource
import com.example.composespotify.app.data.model.AlbumModel
import com.example.composespotify.app.data.model.PlaylistModel

interface DetailRepository {
    suspend fun getAlbum(id: String): Resource<AlbumModel>
    suspend fun getPlaylist(id: String): Resource<PlaylistModel>
}