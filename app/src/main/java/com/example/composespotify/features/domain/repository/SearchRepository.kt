package com.example.composespotify.features.domain.repository

import com.example.composespotify.features.data.model.CategoryIconModel
import com.example.composespotify.features.data.model.PaginatedData
import com.example.composespotify.features.data.model.PlaylistModel

interface SearchRepository {
    suspend fun getCategories(offset: Int, limit: Int): PaginatedData<CategoryIconModel>?

    suspend fun getCategoryPlaylist(id: String, offset: Int, limit: Int): PaginatedData<PlaylistModel>?
}