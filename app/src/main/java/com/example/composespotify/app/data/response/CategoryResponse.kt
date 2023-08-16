package com.example.composespotify.app.data.response

import com.example.composespotify.app.data.model.CategoryIconModel
import com.example.composespotify.app.data.model.PaginatedData

data class CategoryResponse(
    val categories: PaginatedData<CategoryIconModel>
)
