package com.example.composespotify.features.data.response

import com.example.composespotify.features.data.model.CategoryIconModel
import com.example.composespotify.features.data.model.PaginatedData

data class CategoryResponse(
    val categories: PaginatedData<CategoryIconModel>
)
