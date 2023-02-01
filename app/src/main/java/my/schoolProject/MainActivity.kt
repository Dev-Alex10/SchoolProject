package my.schoolProject


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* works
        val intent = YouTubeStandalonePlayer.createVideoIntent(this, api_key, "JwmnF4d30DE");
        startActivity(intent);
        */
        setContent {
            SchoolApp()
        }
    }
/*
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
        Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
            .show()
    }*/
}
