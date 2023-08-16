package com.example.composespotify.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composespotify.app.data.model.toFeaturedPlaylist
import com.example.composespotify.core.resource.Resource
import com.example.composespotify.app.data.model.RecommendationsModel
import com.example.composespotify.app.data.model.toNewReleases
import com.example.composespotify.app.domain.repository.HomeRepository
import com.example.composespotify.app.domain.entity.HomeFeedEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val fetchingHomeFeed: Boolean = false,
    val fetchingHomeFeedErr: String = "",
    val homeFeed: MutableList<HomeFeedEntity> = mutableListOf(),

    val fetchingRecommendations: Boolean = false,
    val fetchingRecommendationsErr: String = "",
    val recommendations: List<RecommendationsModel> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        fetchRecommendations()
        fetchHomeFeed()
    }

    private fun fetchRecommendations() {
        _homeState.update {
            it.copy(
                fetchingRecommendations = true,
                fetchingRecommendationsErr = ""
            )
        }
        viewModelScope.launch {
            when (val res = homeRepository.getRecommendations(
                6,
                "4NHQUGzhtTLFvgF5SZesLK",
                "classical,country",
                "0c6xIDDpzE81m2q797ordA"
            )) {
                is Resource.Success -> {
                    _homeState.update { it.copy(recommendations = res.data!!) }
                }
                else -> {
                    _homeState.update { it.copy(fetchingRecommendationsErr = res.message!!) }
                }
            }
            _homeState.update { it.copy(fetchingHomeFeed = false) }
        }
    }

    private fun fetchHomeFeed() {
        _homeState.update { it.copy(fetchingHomeFeed = true, fetchingHomeFeedErr = "") }
        viewModelScope.launch {
            when (val res = homeRepository.getFeaturedPlaylist(0, 10)) {
                is Resource.Success -> {
                    _homeState.value.homeFeed.add(res.data!!.toFeaturedPlaylist())
                }
                else -> {
                    _homeState.update { it.copy(fetchingHomeFeedErr = res.message!!) }
                }
            }
            when (val res = homeRepository.getNewReleases(0, 10)) {
                is Resource.Success -> {
                    _homeState.value.homeFeed.add(res.data!!.toNewReleases())
                }
                else -> {
                    _homeState.update { it.copy(fetchingHomeFeedErr = res.message!!) }
                }
            }
            _homeState.update { it.copy(fetchingHomeFeed = false) }
        }
    }
}