package my.schoolProject.settings.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import my.schoolProject.settings.presentation.SettingsRoot

fun NavGraphBuilder.settingsGraph(
    modifier: Modifier,
) {
    navigation<SettingsGraphRoutes.Graph>(
        startDestination = SettingsGraphRoutes.Settings
    ) {
        composable<SettingsGraphRoutes.Settings> {
            SettingsRoot(
                modifier = modifier,
            )
        }
    }
}