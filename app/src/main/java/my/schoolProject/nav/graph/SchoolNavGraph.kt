package my.schoolProject.nav.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import my.schoolProject.nav.destinations.Classroom
import my.schoolProject.nav.destinations.Lesson
import my.schoolProject.nav.destinations.Login
import my.schoolProject.nav.destinations.Profile
import my.schoolProject.nav.destinations.Register
import my.schoolProject.ui.lesson.LessonView
import my.schoolProject.ui.login.LoginView
import my.schoolProject.ui.profile.ProfileView
import my.schoolProject.ui.quiz.QuizView
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
            QuizView(onClickSignOut = {
                navController.navigateSingleTopTo(Login.route)
            })
        }
        //Profile
        composable(route = Profile.route) {
            ProfileView()
        }
        //Lesson
        composable(route = Lesson.route) {
            LessonView()
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
