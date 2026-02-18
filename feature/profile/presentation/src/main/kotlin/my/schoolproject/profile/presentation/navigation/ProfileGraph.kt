package my.schoolproject.profile.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import my.schoolproject.profile.presentation.ProfileRoot
import my.schoolproject.profile.presentation.profile_edit.ProfileEditRoot

fun NavGraphBuilder.profileGraph(
    modifier: Modifier,
    navController: NavController,
) {
    navigation<ProfileGraphRoutes.Graph>(
        startDestination = ProfileGraphRoutes.Profile,
    ) {
        composable<ProfileGraphRoutes.Profile> {
            ProfileRoot(
                modifier = modifier,
                onEditClick = {
                    navController.navigate(ProfileGraphRoutes.ProfileEdit)
                }
            )
        }
        composable<ProfileGraphRoutes.ProfileEdit> {
            ProfileEditRoot(
                modifier = modifier,
                onSaveSuccess = {
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}