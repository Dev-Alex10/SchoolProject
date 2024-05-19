package com.example.lesson.ui

import android.content.Context
import androidx.annotation.OptIn
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor() : ViewModel() {
    private val urlYt =
        "https://youtu.be/xc8nAcVvpxY"
    private val url = "https://filesamples.com/samples/video/mp4/sample_3840x2160.mp4"
    var uiState = mutableStateOf(LessonUiState())
        private set

    @OptIn(UnstableApi::class)
    fun prepareVideo(context: Context) {
        val exoPlayer = ExoPlayer.Builder(context).build().apply {
            val dataSourceFactory = DefaultDataSource.Factory(
                context
            )
            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(url))
            setMediaSource(source)
            prepare()
        }
        uiState.value = uiState.value.copy(exoPlayer = exoPlayer)
    }

    override fun onCleared() {
        if (uiState.value.exoPlayer != null) {
            uiState.value.exoPlayer?.release()
        }
        super.onCleared()
    }
}
