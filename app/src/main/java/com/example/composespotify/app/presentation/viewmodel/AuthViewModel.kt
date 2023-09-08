package com.example.composespotify.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composespotify.core.resource.Resource
import com.example.composespotify.core.service.DataStoreService
import com.example.composespotify.app.domain.repository.AuthRepository
import com.example.composespotify.core.service.SnackBarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

data class AuthState(
    val gettingToken: Boolean = false,
    val tokenIsValid: Boolean = false,
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreService: DataStoreService
) : ViewModel() {
    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    init { validateToken() }

    private fun validateToken() {
        viewModelScope.launch {
            val tokenExp = dataStoreService.getTokenExp()
            if (tokenExp == null) {
                _authState.update { it.copy(tokenIsValid = false) }
                return@launch
            }
            _authState.update {
                it.copy(
                    tokenIsValid = tokenExp.isAfter(LocalDateTime.now()),
                )
            }
        }
    }

    fun getAndSetToken() {
        _authState.update { it.copy(gettingToken = true) }
        viewModelScope.launch {
            when (val res = authRepository.getAndSetToken()) {
                is Resource.Error -> SnackBarService.displayMessage(res.message!!)
                is Resource.Success -> { validateToken() }
            }
            _authState.update { it.copy(gettingToken = false) }
        }
    }
}