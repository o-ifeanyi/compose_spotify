package com.example.composespotify.features.domain.entity


enum class FeedType {
    Playlist,
    Album
}

data class HomeFeedEntity(
    val id: String,
    val title: String,
    val data: List<HomeFeedData>
)

data class HomeFeedData(
    val id: String,
    val type: FeedType,
    val url: String,
    val header: String,
    val subtitle: String
)
