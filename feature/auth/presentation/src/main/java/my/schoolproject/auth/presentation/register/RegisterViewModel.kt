package my.schoolproject.auth.presentation.register

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import my.schoolproject.auth.presentation.AuthViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : AuthViewModel<RegisterState>(RegisterState()) {

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

    }

}
