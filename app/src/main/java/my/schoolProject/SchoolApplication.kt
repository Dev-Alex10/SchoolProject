package my.schoolProject

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import my.schoolProject.navigation.NavigationRoot
import my.schoolProject.ui.theme.MyApplicationTheme
import my.schoolproject.auth.presentation.navigation.AuthGraphRoutes

@HiltAndroidApp
class SchoolApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    MyApplicationTheme {
        NavigationRoot(
            navController,
            startDestination = AuthGraphRoutes.Graph
        )
    }
}