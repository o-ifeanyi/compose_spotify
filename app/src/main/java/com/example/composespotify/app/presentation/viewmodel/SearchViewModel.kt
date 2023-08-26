package com.example.composespotify.app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.composespotify.core.resource.PagingResource
import com.example.composespotify.core.resource.Resource
import com.example.composespotify.app.data.model.PlaylistModel
import com.example.composespotify.app.data.response.toSearchEntity
import com.example.composespotify.app.domain.entity.SearchEntity
import com.example.composespotify.app.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchState(
    val searching: Boolean = true,
    val searchingErr: String = "",
    val searchEntity: SearchEntity? = null
)

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository): ViewModel() {
    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    val searchText = MutableStateFlow("")

    init {
        viewModelScope.launch {
            searchText.asStateFlow().debounce(1000).collect { query ->
                Log.d("SEARCH QUERY", query)
                if (query.isEmpty()) return@collect
                _searchState.update { it.copy(searching = true, searchingErr = "") }
                viewModelScope.launch {
                    when(val res = searchRepository.search(query, 0, 20)) {
                        is Resource.Success -> {
                            _searchState.update { it.copy(searchEntity = res.data?.toSearchEntity()) }
                        }
                        is Resource.Error -> {
                            _searchState.update { it.copy(searchingErr = res.message!!) }
                        }
                    }
                    _searchState.update { it.copy(searching = false) }
                }
            }
        }
    }

    val categories = Pager(initialKey = 0 ,config = PagingConfig(initialLoadSize = 20, pageSize = 20)) {
        PagingResource {
            searchRepository.getCategories(it, 20)
        }
    }.flow.cachedIn(viewModelScope)

    fun getCategoryPlaylist(id: String): Flow<PagingData<PlaylistModel>> {
        val categoryPlaylist = Pager(initialKey = 0 ,config = PagingConfig(initialLoadSize = 20, pageSize = 20)) {
            PagingResource {
                searchRepository.getCategoryPlaylist(id, it, 20)
            }
        }.flow.cachedIn(viewModelScope)

        return categoryPlaylist
    }
}