package my.schoolproject.auth.presentation.register

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import my.schoolproject.auth.presentation.AuthViewModel
import my.schoolproject.core.domain.DataError
import my.schoolproject.core.domain.onFailure
import my.schoolproject.core.domain.onSuccess
import my.schoolproject.domain.auth.AuthRepository
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : AuthViewModel<RegisterState>(RegisterState()) {
    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()


    private val isConfirmPasswordValidFlow =
        snapshotFlow { state.value.confirmPasswordTextState.text.toString() }
            .map { confirmPassword ->
                confirmPassword == state.value.passwordTextState.text.toString() && confirmPassword.isNotEmpty()
            }
            .distinctUntilChanged()


    override fun observeValidationStates() {
        combine(
            isEmailValidFlow,
            isPasswordValidFlow,
            isConfirmPasswordValidFlow
        ) { isEmailValid, isPasswordValid, isConfirmPasswordValid ->
            val allValid = isEmailValid && isPasswordValid && isConfirmPasswordValid

            _state.value = _state.value.copy(
                canRegister = allValid,
                isEmailValid = isEmailValid,
                isPasswordValid = isPasswordValid,
                isConfirmPasswordValid = isConfirmPasswordValid
            )
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
                if (it == DataError.Remote.CONFLICT) {
                    eventChannel.send(RegisterEvent.OnError("User with this email already exists"))
                }else{
                    eventChannel.send(RegisterEvent.OnError(it.toString()))
                }
            }
        }
    }
}
