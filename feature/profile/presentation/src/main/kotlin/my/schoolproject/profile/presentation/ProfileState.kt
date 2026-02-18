package my.schoolproject.profile.presentation

data class ProfileState(
    val name: String = "",
    val email: String = "",
    val photoUrl: String? = null,
    val isLoading: Boolean = false
)
