package my.schoolproject.profile.presentation

sealed interface ProfileAction {
    data object OnEditClick : ProfileAction
}