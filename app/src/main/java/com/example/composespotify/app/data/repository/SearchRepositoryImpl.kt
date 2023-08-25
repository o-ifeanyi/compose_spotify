package com.example.composespotify.app.data.repository

import android.util.Log
import com.example.composespotify.core.resource.Resource
import com.example.composespotify.core.service.ApiService
import com.example.composespotify.app.data.model.CategoryIconModel
import com.example.composespotify.app.data.model.PaginatedData
import com.example.composespotify.app.data.model.PlaylistModel
import com.example.composespotify.app.data.response.SearchResponse
import com.example.composespotify.app.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val apiService: ApiService): SearchRepository {
    override suspend fun getCategories(offset: Int, limit: Int): PaginatedData<CategoryIconModel>? {
        return try {
            val res = apiService.getCategories(offset, limit)
            res.categories
        } catch (e: Exception) {
            Log.d("getCategories failure", e.stackTraceToString())
            null
        }
    }

    override suspend fun getCategoryPlaylist(
        id: String,
        offset: Int,
        limit: Int
    ): PaginatedData<PlaylistModel>? {
        return try {
            val res = apiService.getCategoryPlaylist(id, offset, limit)
            res.playlists
        } catch (e: Exception) {
            Log.d("getCategoryPlaylist failure", e.stackTraceToString())
            null
        }
    }

    override suspend fun search(
        query: String,
        offset: Int,
        limit: Int
    ): Resource<SearchResponse> {
        return try {
            val res = apiService.search(query, "artist,playlist,album,track", offset, limit)
            Log.d("search success", res.toString())
            Resource.Success(res)
        } catch (e: Exception) {
            Log.d("search failure", e.stackTraceToString())
            Resource.Error(message = e.message ?: "Something went wrong")
        }
    }
}