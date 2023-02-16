package my.schoolProject.ui.lesson

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import com.google.android.youtube.player.YouTubeStandalonePlayer
import my.schoolProject.databinding.YoutubeViewerBinding

private var API_KEY = "AIzaSyARh5mcLg-BWEZ_dxkCEm8WCNKqeGyFXOU"

@Composable
fun LessonView(
    lessonViewModel: LessonViewModel = hiltViewModel(),
    openYoutube: () -> Unit
) {
    val context = LocalContext.current
//    var ytPlayer: YouTubePlayerFragment =
//        fragmentManager.findFragmentById(R.id.ytPlayer) as YouTubePlayerFragment
    val uiState by lessonViewModel.uiState
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
//        AndroidView(factory = { contextFactory ->
//            lessonViewModel.prepareVideo(context)
//            StyledPlayerView(contextFactory).apply {
//                player = uiState.exoPlayer
//            }
//        })
        AndroidViewBinding(factory = YoutubeViewerBinding::inflate) {
            val myFragment = ytFragmentView.getFragment<YouTubePlayerSupportFragmentX>()
            lessonViewModel.prepareYouTubeVideo(myFragment, context)
        }
        //Starts new activity, only button is visible in View
//        Button(onClick = { openYoutube() }) {
//            Text(text = "Start Video")
//        }
    }
}

