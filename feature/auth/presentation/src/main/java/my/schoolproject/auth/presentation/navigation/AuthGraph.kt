package my.schoolproject.auth.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import my.schoolproject.auth.presentation.register.RegisterScreen
import my.schoolproject.auth.presentation.register.RegisterViewModel

fun NavGraphBuilder.authGraph(
    navController: NavController,
    onLoginSuccess: () -> Unit,
) {
    navigation<AuthGraphRoutes.Graph>(
        startDestination = AuthGraphRoutes.Login,
    ) {
        composable<AuthGraphRoutes.Login> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    navController.navigate(AuthGraphRoutes.Register)
                }) {
                    Text("Register")
                }
            }
        }

        composable<AuthGraphRoutes.Register> {
            val registerViewModel = hiltViewModel<RegisterViewModel>()
            RegisterScreen(registerViewModel = registerViewModel)
        }
    }
}