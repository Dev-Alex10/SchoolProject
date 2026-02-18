package my.schoolproject.profile.presentation.profile_edit

sealed interface ProfileEditAction {
    data class OnNameChange(val name: String) : ProfileEditAction
    data class OnEmailChange(val email: String) : ProfileEditAction
    data object OnPhotoClick : ProfileEditAction
    data object OnSaveClick : ProfileEditAction
    data object OnCancelClick : ProfileEditAction
}