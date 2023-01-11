package my.schoolProject.nav.destinations

interface SchoolDestination {
    val route: String
}
object Login: SchoolDestination {
    override val route = "Login"
}
object Register: SchoolDestination {
    override val route = "Register"
}

