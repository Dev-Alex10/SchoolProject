package my.schoolProject.nav.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

interface BottomBarDestination {
    val route: String
    val icon: ImageVector
}

object Classroom : BottomBarDestination {
    override val route = "Classroom"
    override val icon = Icons.Filled.Home
}

object Profile : BottomBarDestination {
    override val route = "Profile"
    override val icon = Icons.Filled.AccountCircle
}

object Lesson : BottomBarDestination {
    override val route = "Lesson"
    override val icon = Icons.Filled.Search
}
