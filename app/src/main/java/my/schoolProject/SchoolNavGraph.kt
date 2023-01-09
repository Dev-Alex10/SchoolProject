package my.schoolProject

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import my.schoolProject.ui.classroom.ClassroomView
import my.schoolProject.ui.login.LoginView
import my.schoolProject.ui.register.RegisterView

@Composable
fun SchoolNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = if (Firebase.auth.currentUser == null) Login.route else Classroom.route,
        modifier = modifier
    ) {
        //Login
        composable(Login.route) {
            LoginView(modifier = modifier, onClickCreateAccount = {
                navController.navigateSingleTopTo(Register.route)
            }, onClickLogin = {
                navController.navigateSingleTopTo(Classroom.route)
            })
        }
        //Register
        composable(route = Register.route) {
            RegisterView(modifier = modifier, onClickRegister = {
                navController.navigateSingleTopTo(Classroom.route)
            })
        }
        //Question Answer
        composable(route = Classroom.route) {
            ClassroomView(
                modifier = modifier,
                onClickSignOut = {
                    navController.navigateSingleTopTo(Login.route)
                }
            )
        }
    }
}

//Only one instance of each route
fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
