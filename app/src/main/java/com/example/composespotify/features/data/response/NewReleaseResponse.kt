package com.example.composespotify.features.data.response

import com.example.composespotify.features.data.model.NewReleasesModel
import com.example.composespotify.features.data.model.PaginatedData

data class NewReleaseResponse(
    val albums: PaginatedData<NewReleasesModel>
)
