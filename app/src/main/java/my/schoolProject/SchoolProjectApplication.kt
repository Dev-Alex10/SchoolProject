package my.schoolProject

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.HiltAndroidApp
import my.schoolProject.nav.graph.BottomBarNavHost
import my.schoolProject.nav.graph.SchoolNavHost
import my.schoolProject.ui.theme.SchoolTheme
import javax.inject.Inject

@HiltAndroidApp
class SchoolProjectApplication @Inject constructor() : Application()

@Composable
fun SchoolApp(openYoutube: () -> Unit) {
    SchoolTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomBarNavHost(navController = navController)
            }
        ) {
            SchoolNavHost(
                navController = navController,
                modifier = Modifier.padding(it),
                openYoutube = openYoutube
            )
        }
    }
}
