package com.example.composespotify.app.domain.repository

import com.example.composespotify.core.resource.Resource
import com.example.composespotify.app.data.model.CategoryIconModel
import com.example.composespotify.app.data.model.PaginatedData
import com.example.composespotify.app.data.model.PlaylistModel
import com.example.composespotify.app.data.response.SearchResponse

interface SearchRepository {
    suspend fun getCategories(offset: Int, limit: Int): PaginatedData<CategoryIconModel>?

    suspend fun getCategoryPlaylist(id: String, offset: Int, limit: Int): PaginatedData<PlaylistModel>?

    suspend fun search(query: String, type: String, offset: Int, limit: Int) : Resource<SearchResponse>
}