package com.example.composespotify.app.domain.entity

data class SearchEntity(
    val tracks: List<TrackDataEntity>,
    val albums: List<HomeFeedData>,
    val playlists: List<HomeFeedData>
)