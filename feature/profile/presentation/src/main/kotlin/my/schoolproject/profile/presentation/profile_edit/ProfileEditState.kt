package my.schoolproject.profile.presentation.profile_edit

import androidx.compose.foundation.text.input.TextFieldState
import my.schoolproject.core.domain.auth.user.User

data class ProfileEditState(
    val nameTextFieldState: TextFieldState = TextFieldState(),
    val isNameValid: Boolean = false,
    val emailTextFieldState: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = false,
    val canSave: Boolean = false,
    val isSaving: Boolean = false,
    val photoUrl: String? = null,
)

fun ProfileEditState.toDomain(uid: String) =
    User(
        uid = uid,
        name = nameTextFieldState.text.trim().toString(),
        email = emailTextFieldState.text.trim().toString(),
        photoUrl = photoUrl?.trim()
    )