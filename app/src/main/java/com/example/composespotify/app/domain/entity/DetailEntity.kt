package com.example.composespotify.app.domain.entity

data class DetailEntity(
    val id: String,
    val url: String,
    val name: String,
    val description: String,
    val duration: String,
    val tracks: List<TrackDataEntity>
)

data class TrackDataEntity(
    val id: String,
    val url: String,
    val title: String,
    val subtitle: String,
    val previewUrl: String,
)