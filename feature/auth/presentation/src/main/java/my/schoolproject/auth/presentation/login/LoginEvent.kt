package my.schoolproject.auth.presentation.login

sealed interface LoginEvent {
    data object OnSuccess : LoginEvent
    data class OnError(val error: String) : LoginEvent
}