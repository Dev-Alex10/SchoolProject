package my.schoolProject


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import dagger.hilt.android.AndroidEntryPoint

private var API_KEY = "AIzaSyARh5mcLg-BWEZ_dxkCEm8WCNKqeGyFXOU"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = YouTubeStandalonePlayer.createVideoIntent(this, API_KEY, "xc8nAcVvpxY");
//        startActivity(intent);

        setContent {
            SchoolApp(openYoutube = { startActivity(intent) })
        }
    }
}
