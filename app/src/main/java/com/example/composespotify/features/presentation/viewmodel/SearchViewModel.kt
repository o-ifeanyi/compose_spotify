package com.example.composespotify.features.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.composespotify.core.resource.PagingResource
import com.example.composespotify.features.data.model.PlaylistModel
import com.example.composespotify.features.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//data class SearchState(
//    val fetching: Boolean = false,
//)

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository): ViewModel() {
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