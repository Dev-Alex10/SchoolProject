package my.schoolproject.profile.presentation.profile_edit

import android.net.Uri

sealed interface ProfileEditAction {
    data object OnSaveClick : ProfileEditAction
    data object OnCancelClick : ProfileEditAction
    data object OnPhotoClick : ProfileEditAction
    data object OnRemovePhotoClick : ProfileEditAction
    data class OnPhotoSelected(val uri: Uri) : ProfileEditAction
}