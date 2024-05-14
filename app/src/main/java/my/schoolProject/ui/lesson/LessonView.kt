package my.schoolProject.ui.lesson

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerControlView
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
fun LessonView(
    lessonViewModel: LessonViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState by lessonViewModel.uiState
    AndroidView(modifier = Modifier.fillMaxSize(), factory = { contextFactory ->
        lessonViewModel.prepareVideo(context)
        PlayerView(contextFactory).apply {
            player = uiState.exoPlayer
        }
    })
}

