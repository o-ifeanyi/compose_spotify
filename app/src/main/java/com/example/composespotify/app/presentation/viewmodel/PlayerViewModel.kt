package com.example.composespotify.app.presentation.viewmodel

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composespotify.app.domain.entity.TrackDataEntity
import com.example.composespotify.core.service.SnackBarService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PlayState(
    val isPlaying: Boolean = false,
    val nowPlaying: TrackDataEntity? = null,
    val elapsedTime: Int = 0,
    val queue: List<TrackDataEntity> = emptyList(),
)
object PlayerViewModel : ViewModel()  {
    private val _playState = MutableStateFlow(PlayState())
    val playState = _playState.asStateFlow()
    private var mediaPlayer: MediaPlayer? = null

    private suspend fun listen() {
        mediaPlayer?.setOnCompletionListener {
            next()
        }
        while (mediaPlayer?.isPlaying == true) {
            delay(1000L)
            _playState.update { it.copy(elapsedTime = mediaPlayer?.currentPosition ?: 0) }
        }
    }

    fun play(track: TrackDataEntity) {
        try {
            mediaPlayer?.stop()
            if (track != _playState.value.nowPlaying) {
                _playState.update { it.copy(elapsedTime = 0) }
            }
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setDataSource(track.previewUrl)
            mediaPlayer?.prepare()
            mediaPlayer?.seekTo(_playState.value.elapsedTime)
            mediaPlayer?.start()

            _playState.update { it.copy(isPlaying = mediaPlayer!!.isPlaying, nowPlaying = track) }
            viewModelScope.launch { listen() }
        } catch (e: Exception) {
            e.printStackTrace()
            SnackBarService.displayMessage("An error occurred while playing track")
        }
    }

    fun resume() {
        if (_playState.value.nowPlaying != null) {
            play(_playState.value.nowPlaying!!)
        }
    }

    fun setQueue(value: List<TrackDataEntity>) {
        _playState.update { it.copy(queue = value) }
    }

    fun pause() {
        if (mediaPlayer != null) {
            mediaPlayer!!.pause()
            _playState.update { it.copy(isPlaying = mediaPlayer!!.isPlaying) }
        }
    }

    fun prev() {
        val state = _playState.value
        if (state.nowPlaying != null && state.queue.isNotEmpty()) {
            val index = state.queue.indexOf(state.nowPlaying)
            if (index > 0) {
                play(state.queue[index - 1])
            } else {
                play(state.queue[0])
            }
        }
    }

    fun next() {
        val state = _playState.value
        if (state.nowPlaying != null && state.queue.isNotEmpty()) {
            val index = state.queue.indexOf(state.nowPlaying)
            if (index < state.queue.lastIndex) {
                play(state.queue[index + 1])
            } else {
                play(state.queue[0])
            }
        }
    }
}