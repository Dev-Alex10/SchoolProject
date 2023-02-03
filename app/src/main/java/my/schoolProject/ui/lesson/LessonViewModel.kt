package my.schoolProject.ui.lesson

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private var API_KEY = "AIzaSyARh5mcLg-BWEZ_dxkCEm8WCNKqeGyFXOU"

@HiltViewModel
class LessonViewModel @Inject constructor() : ViewModel() {
    val urlYt =
        "https://youtu.be/JwmnF4d30DE"
    private val url =
        "https://joy1.videvo.net/videvo_files/video/free/2014-12/large_watermarked/Raindrops_Videvo_preview.mp4"

    fun prepareVideo(context: Context): ExoPlayer {
        val exoPlayer = ExoPlayer.Builder(context).build().apply {
            val dataSourceFactory = DefaultDataSource.Factory(
                context
            )
            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(url))
            setMediaSource(source)
            prepare()
        }
        return exoPlayer
    }

    fun prepareYouTubeVideo(myFragment: YouTubePlayerSupportFragmentX, context: Context) {
        viewModelScope.launch {
            myFragment.initialize(API_KEY, object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    player: YouTubePlayer?,
                    wasResumed: Boolean
                ) {
                    if (player == null) return
                    if (wasResumed) {
                        player.play()
                    } else {
                        player.cueVideo("xc8nAcVvpxY")
                        player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    }
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider?,
                    result: YouTubeInitializationResult?
                ) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }
}
