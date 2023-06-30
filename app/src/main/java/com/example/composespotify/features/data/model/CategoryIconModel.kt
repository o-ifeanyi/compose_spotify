package com.example.composespotify.features.data.model

data class CategoryIconModel(
    val id : String,
    val href : String,
    val icons: List<ImageModel>,
    val name: String
)
