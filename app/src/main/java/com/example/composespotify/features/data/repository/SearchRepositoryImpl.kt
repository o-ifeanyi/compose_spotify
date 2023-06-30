package com.example.composespotify.features.data.repository

import android.util.Log
import com.example.composespotify.core.service.ApiService
import com.example.composespotify.features.data.model.CategoryIconModel
import com.example.composespotify.features.data.model.PaginatedData
import com.example.composespotify.features.data.model.PlaylistModel
import com.example.composespotify.features.domain.repository.SearchRepository
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
}