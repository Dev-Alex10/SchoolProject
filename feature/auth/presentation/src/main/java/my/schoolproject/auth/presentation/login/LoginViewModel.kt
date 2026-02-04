package my.schoolproject.auth.presentation.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import my.schoolproject.auth.presentation.AuthViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : AuthViewModel<LoginState>(LoginState()) {

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
//            authRepository.signOut()
        }
    }

    fun login() {
    }
}