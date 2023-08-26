package com.example.composespotify.app.domain.entity

data class SearchEntity(
    val tracks: List<TrackDataEntity>,
    val artists: List<TrackDataEntity>,
    val albums: List<TrackDataEntity>,
    val playlists: List<TrackDataEntity>
)