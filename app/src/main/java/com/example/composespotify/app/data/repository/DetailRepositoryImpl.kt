package com.example.composespotify.app.data.repository

import android.util.Log
import com.example.composespotify.core.resource.Resource
import com.example.composespotify.core.service.ApiService
import com.example.composespotify.app.data.model.AlbumModel
import com.example.composespotify.app.data.model.PlaylistModel
import com.example.composespotify.app.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val apiService: ApiService): DetailRepository {
    override suspend fun getAlbum(id: String): Resource<AlbumModel> {
        return try {
            val res = apiService.getAlbum(id)
            Resource.Success(data = res)
        } catch (e: Exception) {
            Log.d("getAlbum failure", e.stackTraceToString())
            Resource.Error(message = e.message ?: "Something went wrong")
        }
    }

    override suspend fun getPlaylist(id: String): Resource<PlaylistModel> {
        return try {
            val res = apiService.getPlaylist(id)
            Resource.Success(data = res)
        } catch (e: Exception) {
            Log.d("getPlaylist failure", e.stackTraceToString())
            Resource.Error(message = e.message ?: "Something went wrong")
        }
    }
}