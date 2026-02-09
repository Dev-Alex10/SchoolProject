package my.schoolproject.auth.presentation

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import my.schoolproject.auth.presentation.validation.EmailValidator
import my.schoolproject.auth.presentation.validation.PasswordValidator

abstract class AuthViewModel<T : AuthTextFieldsState>(initialState: T) : ViewModel() {
    private var hasLoadedInitialData = false
    protected val _state = MutableStateFlow(initialState)
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
            initialValue = initialState
        )

    protected val isEmailValidFlow: Flow<Boolean> =
        snapshotFlow { state.value.emailTextState.text.toString() }
            .map { email -> EmailValidator.validate(email) }
            .distinctUntilChanged()

    protected val isPasswordValidFlow: Flow<Boolean> =
        snapshotFlow { state.value.passwordTextState.text.toString() }
            .map { password -> PasswordValidator.validate(password) }
            .distinctUntilChanged()

    protected abstract fun observeValidationStates()
}