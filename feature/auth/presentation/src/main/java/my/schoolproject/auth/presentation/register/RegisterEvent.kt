package my.schoolproject.auth.presentation.register

sealed interface RegisterEvent {
    data object OnSuccess : RegisterEvent
    data class OnError(val error: String) : RegisterEvent
}