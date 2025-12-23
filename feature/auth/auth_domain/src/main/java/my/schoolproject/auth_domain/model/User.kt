package my.schoolproject.auth_domain.model

data class User(
    val name: String,
    val email: String,
    val photoUrl: String?,
    val idToken: String
)
