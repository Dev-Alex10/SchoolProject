package my.schoolproject.auth.presentation.register

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import my.schoolproject.auth.presentation.AuthViewModel
import my.schoolproject.core.domain.DataError
import my.schoolproject.core.domain.auth.AuthRepository
import my.schoolproject.core.domain.onFailure
import my.schoolproject.core.domain.onSuccess
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : AuthViewModel<RegisterState>(RegisterState()) {
    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()


    private val isConfirmPasswordValidFlow =
        combine(
            snapshotFlow { state.value.passwordTextState.text.toString() },
            snapshotFlow { state.value.confirmPasswordTextState.text.toString() }
        ) { password, confirmPassword ->
            password == confirmPassword && confirmPassword.isNotEmpty()
        }.distinctUntilChanged()


    override fun observeValidationStates() {
        combine(
            isEmailValidFlow,
            isPasswordValidFlow,
            isConfirmPasswordValidFlow
        ) { isEmailValid, isPasswordValid, isConfirmPasswordValid ->
            val allValid = isEmailValid && isPasswordValid && isConfirmPasswordValid

            _state.update {
                it.copy(
                    canRegister = allValid,
                    isEmailValid = isEmailValid,
                    isPasswordValid = isPasswordValid,
                    isConfirmPasswordValid = isConfirmPasswordValid
                )
            }
        }.launchIn(viewModelScope)
    }

    fun register() {
        viewModelScope.launch {
            repository.register(
                state.value.emailTextState.text.toString(),
                state.value.passwordTextState.text.toString()
            ).onSuccess {
                eventChannel.send(RegisterEvent.OnSuccess)
            }.onFailure {
                when (it) {
                    DataError.Remote.CONFLICT -> eventChannel.send(RegisterEvent.OnError("User with this email already exists"))
                    DataError.Remote.UNAUTHORIZED -> eventChannel.send(RegisterEvent.OnError("Invalid email address"))
                    else -> eventChannel.send(RegisterEvent.OnError("Something went wrong"))
                }
            }
        }
    }
}
