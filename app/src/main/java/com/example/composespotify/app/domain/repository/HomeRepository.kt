package com.example.composespotify.app.domain.repository

import com.example.composespotify.core.resource.Resource
import com.example.composespotify.app.data.model.*

interface HomeRepository {
    suspend fun getRecommendations(limit: Int, seedArtists: String, seedGenres: String, seedTracks: String): Resource<List<RecommendationsModel>>
    suspend fun getFeaturedPlaylist(offset: Int, limit: Int): Resource<PaginatedData<PlaylistModel>>
    suspend fun getNewReleases(offset: Int, limit: Int): Resource<PaginatedData<NewReleasesModel>>
}