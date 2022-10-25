package my.schoolProject

import android.app.Application
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.HiltAndroidApp
import my.schoolProject.data.source.local.AppDatabase
import my.schoolProject.data.source.domain.user.DefaultUserRepository
import my.schoolProject.ui.theme.SchoolTheme
import javax.inject.Inject

@HiltAndroidApp
class SchoolProjectApplication @Inject constructor() : Application()

@Composable
fun SchoolApp(){
    SchoolTheme {
        val navController = rememberNavController()
        Scaffold {
            //TODO do navHost
        }
    }
}
