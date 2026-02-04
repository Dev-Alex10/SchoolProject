package my.schoolproject.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import my.schoolproject.auth.presentation.validation.EmailValidator
import my.schoolproject.auth.presentation.validation.PasswordValidator
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _email: MutableStateFlow<String> = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun onEmailTextChange(email: String) {
        _email.value = email
    }

    fun onPasswordTextChange(password: String) {
        _password.value = password
    }

    fun isEmailValid(): Boolean {
        return EmailValidator.validate(_email.value)
    }

    fun isPasswordValid(): Boolean {
        return PasswordValidator.validate(_password.value)
    }

    fun areCredentialsValid(): Boolean {
        return isEmailValid() && isPasswordValid()
    }

    fun logout() {
        viewModelScope.launch {
//            authRepository.signOut()
        }
    }

    fun login() {
        clearState()
    }

    private fun clearState() {
        _email.value = ""
        _password.value = ""
    }
}