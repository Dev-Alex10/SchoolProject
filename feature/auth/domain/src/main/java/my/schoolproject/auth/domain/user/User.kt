package my.schoolproject.auth.domain.user

data class User(
    val name: String,
    val email: String,
    val photoUrl: String?,
    val uid: String
)