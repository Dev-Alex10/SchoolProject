package my.schoolProject

interface SchoolDestination {
    val route: String
}

object Profile : SchoolDestination {
    override val route = "profile"
}
object Login: SchoolDestination{
    override val route = "login"
}
object Register: SchoolDestination{
    override val route = "register"
}
object Classroom : SchoolDestination {
    override val route = "classroom"
}
