package com.example.composespotify.app.domain.entity

data class DetailEntity(
    val id: String,
    val url: String,
    val name: String,
    val description: String,
    val duration: String,
    val tracks: List<DetailTrackData>
)

data class DetailTrackData(
    val id: String,
    val url: String,
    val title: String,
    val artist: String,
    val previewUrl: String,
)