package my.schoolproject.auth.presentation.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import my.schoolproject.auth.presentation.AuthViewModel
import my.schoolproject.core.domain.DataError
import my.schoolproject.core.domain.onFailure
import my.schoolproject.core.domain.onSuccess
import my.schoolproject.domain.auth.AuthRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : AuthViewModel<LoginState>(LoginState()) {
    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    override fun observeValidationStates() {
        combine(
            isEmailValidFlow,
            isPasswordValidFlow
        ) { isEmailValid, isPasswordValid ->
            val allValid = isEmailValid && isPasswordValid

            _state.value = _state.value.copy(
                canLogin = allValid,
                isEmailValid = isEmailValid,
                isPasswordValid = isPasswordValid,
            )
        }.launchIn(viewModelScope)
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun login() {
        viewModelScope.launch {
            repository.login(
                state.value.emailTextState.text.toString(),
                state.value.passwordTextState.text.toString()
            ).onSuccess {
                eventChannel.send(LoginEvent.OnSuccess)
            }.onFailure {
                when (it) {
                    DataError.Remote.NOT_FOUND -> eventChannel.send(LoginEvent.OnError("User not found"))
                    DataError.Remote.UNAUTHORIZED -> eventChannel.send(LoginEvent.OnError("Invalid credentials"))
                    else -> {
                        eventChannel.send(LoginEvent.OnError("Unknown error"))
                    }
                }
            }
        }
    }
}