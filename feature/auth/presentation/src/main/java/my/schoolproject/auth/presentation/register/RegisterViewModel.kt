package my.schoolproject.auth.presentation.register

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import my.schoolproject.auth.presentation.validation.EmailValidator
import my.schoolproject.auth.presentation.validation.PasswordValidator
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    private var hasLoadedInitialData = false
    private val _state = MutableStateFlow(RegisterState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                observeValidationStates()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = RegisterState()
        )

    private val isEmailValidFlow = snapshotFlow { state.value.emailTextState.text.toString() }
        .map { email -> EmailValidator.validate(email) }
        .distinctUntilChanged()

    private val isPasswordValidFlow = snapshotFlow { state.value.passwordTextState.text.toString() }
        .map { password -> PasswordValidator.validate(password) }
        .distinctUntilChanged()
    private val isConfirmPasswordValidFlow =
        snapshotFlow { state.value.confirmPasswordTextState.text.toString() }
            .map { confirmPassword ->
                confirmPassword == state.value.passwordTextState.text && confirmPassword.isNotEmpty()
            }
            .distinctUntilChanged()


    fun observeValidationStates() {
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
