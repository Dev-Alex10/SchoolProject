package my.schoolproject.auth.presentation.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import my.schoolproject.auth.presentation.AuthPresentationConstants.EMAIL_REGEX
import my.schoolproject.auth.presentation.AuthPresentationConstants.PASSWORD_REGEX
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password


    fun onEmailTextChange(email: String) {
        _email.value = email
    }

    fun onPasswordTextChange(password: String) {
        _password.value = password
    }

    fun isEmailValid(): Boolean {
        return _email.value.matches(EMAIL_REGEX.toRegex())
    }

    fun isPasswordValid(): Boolean {
        return _password.value.matches(PASSWORD_REGEX.toRegex())
    }

    fun areCredentialsValid(): Boolean {
        return isEmailValid() && isPasswordValid()
    }
}
