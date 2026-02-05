package my.schoolproject.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import my.schoolproject.auth.presentation.login.LoginScreen
import my.schoolproject.auth.presentation.register.RegisterScreen

fun NavGraphBuilder.authGraph(
    navController: NavController,
    onLoginSuccess: () -> Unit,
) {
    navigation<AuthGraphRoutes.Graph>(
        startDestination = AuthGraphRoutes.Login,
    ) {
        composable<AuthGraphRoutes.Login> {
            LoginScreen(
                onLoginClick = onLoginSuccess,
                onRegisterClick = {
                    navController.navigate(AuthGraphRoutes.Register)
                }
            )
        }

        composable<AuthGraphRoutes.Register> {
            RegisterScreen(onBackClick = { navController.popBackStack() })
        }
    }
}