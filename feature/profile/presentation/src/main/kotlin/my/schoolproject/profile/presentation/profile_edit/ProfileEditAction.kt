package my.schoolproject.profile.presentation.profile_edit

sealed interface ProfileEditAction {
    data object OnPhotoClick : ProfileEditAction
    data object OnSaveClick : ProfileEditAction
    data object OnCancelClick : ProfileEditAction
}