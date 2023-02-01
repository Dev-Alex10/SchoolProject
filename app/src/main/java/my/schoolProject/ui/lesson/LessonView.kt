package my.schoolProject.ui.lesson

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerFragment

private var API_KEY = "AIzaSyARh5mcLg-BWEZ_dxkCEm8WCNKqeGyFXOU"

@Composable
fun LessonView(
    lessonViewModel: LessonViewModel = hiltViewModel()
) {
    val context = LocalContext.current
//    var ytPlayer: YouTubePlayerFragment =
//        fragmentManager.findFragmentById(R.id.ytPlayer) as YouTubePlayerFragment

  /*  val ytFragment = YouTubePlayerFragment.newInstance()
        .initialize(API_KEY, object : OnInitializedListener {
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
        })*/
    val mExoPlayer = remember(context) { //does not work with youtube videos
        lessonViewModel.prepareVideo(context)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        AndroidView(factory = { contextFactory ->
            StyledPlayerView(contextFactory).apply {
                player = mExoPlayer
            }
        })
    }
}

