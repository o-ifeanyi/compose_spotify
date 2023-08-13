package com.example.composespotify.core.service

import com.example.composespotify.features.data.model.CredentialModel
import com.example.composespotify.core.resource.Endpoints
import com.example.composespotify.features.data.model.AlbumModel
import com.example.composespotify.features.data.model.PlaylistModel
import com.example.composespotify.features.data.response.*
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST(value = Endpoints.token)
    suspend fun getToken(
        @Header(value = "isTokenRequest") header: String = "true",
        @Field("grant_type") grantType: String = "client_credentials"
    ): CredentialModel
    @GET(value = Endpoints.recommendations)
    suspend fun getRecommendations(
        @Query(value = "limit") limit: Int,
        @Query(value = "seed_artists") seedArtist: String,
        @Query(value = "seed_genres") seedGenres: String,
        @Query(value = "seed_tracks") seedTracks: String
    ): RecommendationResponse
    @GET(value = Endpoints.featuredPlaylist)
    suspend fun getFeaturedPlaylist(
        @Query(value = "offset") offset: Int,
        @Query(value = "limit") limit: Int
    ): FeaturedPlaylistResponse
    @GET(value = Endpoints.newReleases)
    suspend fun getNewReleases(
        @Query(value = "offset") offset: Int,
        @Query(value = "limit") limit: Int
    ): NewReleaseResponse
    @GET(value = Endpoints.album)
    suspend fun getAlbum(@Path(value = "id") id: String): AlbumModel
    @GET(value = Endpoints.playlist)
    suspend fun getPlaylist(@Path(value = "id") id: String): PlaylistModel
    @GET(value = Endpoints.categories)
    suspend fun getCategories(
        @Query(value = "offset") offset: Int,
        @Query(value = "limit") limit: Int
    ): CategoryResponse
    @GET(value = Endpoints.categoryPlaylist)
    suspend fun getCategoryPlaylist(
        @Path(value = "id") id: String,
        @Query(value = "offset") offset: Int,
        @Query(value = "limit") limit: Int
    ): FeaturedPlaylistResponse
    @GET(value = Endpoints.search)
    suspend fun search(
        @Query(value = "q") query: String,
        @Query(value = "type") type: String,
        @Query(value = "offset") offset: Int,
        @Query(value = "limit") limit: Int
    ): SearchResponse
}