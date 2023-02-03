package my.schoolProject


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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
}
