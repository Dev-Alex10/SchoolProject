package my.schoolproject.profile.presentation.profile_edit

sealed interface ProfileEditEvent {
    data object OnSuccess : ProfileEditEvent
    data class OnError(val error: String) : ProfileEditEvent
}