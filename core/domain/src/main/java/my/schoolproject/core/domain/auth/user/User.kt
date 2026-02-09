package my.schoolproject.core.domain.auth.user

data class User(
    val name: String,
    val email: String,
    val photoUrl: String?,
    val uid: String
)