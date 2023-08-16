package com.example.composespotify.app.data.response

import com.example.composespotify.app.data.model.NewReleasesModel
import com.example.composespotify.app.data.model.PaginatedData

data class NewReleaseResponse(
    val albums: PaginatedData<NewReleasesModel>
)
