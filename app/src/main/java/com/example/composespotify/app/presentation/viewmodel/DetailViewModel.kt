package com.example.composespotify.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composespotify.core.resource.Resource
import com.example.composespotify.app.data.model.toDetailEntity
import com.example.composespotify.app.domain.entity.DetailEntity
import com.example.composespotify.app.domain.repository.DetailRepository
import com.example.composespotify.core.service.SnackBarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailState(
    val gettingPlaylist: Boolean = true,
    val gettingAlbum: Boolean = true,
    val detailEntity: DetailEntity? = null,
)

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepository: DetailRepository) :
    ViewModel() {
    private val _detailState = MutableStateFlow(DetailState())
    val detailState = _detailState.asStateFlow()

    fun fetchAlbum(id: String) {
        _detailState.update { it.copy(gettingAlbum = true) }
        viewModelScope.launch {
            when (val res = detailRepository.getAlbum(id)) {
                is Resource.Success -> _detailState.update { it.copy(detailEntity = res.data!!.toDetailEntity()) }
                is Resource.Error -> SnackBarService.displayMessage(res.message!!)
            }
            _detailState.update { it.copy(gettingAlbum = false) }
        }
    }

    fun fetchPlaylist(id: String) {
        _detailState.update { it.copy(gettingPlaylist = true) }
        viewModelScope.launch {
            when (val res = detailRepository.getPlaylist(id)) {
                is Resource.Success -> _detailState.update { it.copy(detailEntity = res.data!!.toDetailEntity()) }
                is Resource.Error -> SnackBarService.displayMessage(res.message!!)
            }
            _detailState.update { it.copy(gettingPlaylist = false) }
        }
    }
}