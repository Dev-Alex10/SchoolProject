package my.schoolproject.profile.presentation.profile_edit

data class ProfileEditState(
    val name: String = "",
    val email: String = "",
    val photoUrl: String? = null,
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val nameError: String? = null,
    val emailError: String? = null
)
