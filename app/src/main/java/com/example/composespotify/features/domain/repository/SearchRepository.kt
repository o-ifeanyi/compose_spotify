package com.example.composespotify.features.domain.repository

import com.example.composespotify.core.resource.Resource
import com.example.composespotify.features.data.model.CategoryIconModel
import com.example.composespotify.features.data.model.PaginatedData
import com.example.composespotify.features.data.model.PlaylistModel
import com.example.composespotify.features.data.response.SearchResponse

interface SearchRepository {
    suspend fun getCategories(offset: Int, limit: Int): PaginatedData<CategoryIconModel>?

    suspend fun getCategoryPlaylist(id: String, offset: Int, limit: Int): PaginatedData<PlaylistModel>?

    suspend fun search(query: String, type: String, offset: Int, limit: Int) : Resource<SearchResponse>
}