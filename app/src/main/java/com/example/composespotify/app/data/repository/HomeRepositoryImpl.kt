package com.example.composespotify.app.data.repository

import android.util.Log
import com.example.composespotify.app.data.model.PaginatedData
import com.example.composespotify.app.data.model.PlaylistModel
import com.example.composespotify.core.resource.Resource
import com.example.composespotify.core.service.ApiService
import com.example.composespotify.app.data.model.NewReleasesModel
import com.example.composespotify.app.data.model.RecommendationsModel
import com.example.composespotify.app.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val apiService: ApiService): HomeRepository {
    override suspend fun getRecommendations(
        limit: Int,
        seedArtists: String,
        seedGenres: String,
        seedTracks: String
    ): Resource<List<RecommendationsModel>> {
        return try {
            val res = apiService.getRecommendations(limit, seedArtists, seedGenres, seedTracks)
            Resource.Success(data = res.tracks)
        } catch (e: Exception) {
            Log.d("getRecommendations failure", e.stackTraceToString())
            Resource.Error(message = e.message ?: "Something went wrong")
        }
    }

    override suspend fun getFeaturedPlaylist(
        offset: Int,
        limit: Int
    ): Resource<PaginatedData<PlaylistModel>> {
        return try {
            val res = apiService.getFeaturedPlaylist(offset, limit)
            Resource.Success(data = res.playlists)
        } catch (e: Exception) {
            Log.d("getFeaturedPlaylist failure", e.stackTraceToString())
            Resource.Error(message = e.message ?: "Something went wrong")
        }
    }

    override suspend fun getNewReleases(
        offset: Int,
        limit: Int
    ): Resource<PaginatedData<NewReleasesModel>> {
        return try {
            val res = apiService.getNewReleases(offset, limit)
            Resource.Success(data = res.albums)
        } catch (e: Exception) {
            Log.d("getNewReleases failure", e.stackTraceToString())
            Resource.Error(message = e.message ?: "Something went wrong")
        }
    }
}