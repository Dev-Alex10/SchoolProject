package my.schoolProject

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import my.schoolProject.di.DataStoreManager
import my.schoolProject.ui.classroom.ClassroomView
import my.schoolProject.ui.login.LoginView
import my.schoolProject.ui.register.RegisterView

@Composable
fun SchoolNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = Login.route, modifier = modifier) {
        composable(Login.route) {
            LoginView(modifier = modifier, onClickCreateAccount = {
                navController.navigateSingleTopTo(Register.route)
            }, onClickLogin = {
                navController.navigateSingleTopTo(Classroom.route)
            })
        }
        composable(route = Register.route) {
            RegisterView(modifier = modifier, onClickRegister = {
                navController.navigateSingleTopTo(Classroom.route)
            })
        }
        composable(route = Classroom.route) {
            ClassroomView(modifier = modifier)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
