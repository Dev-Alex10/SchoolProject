package my.schoolproject.auth.presentation.login

import androidx.compose.foundation.text.input.TextFieldState
import my.schoolproject.auth.presentation.AuthTextFieldsState

data class LoginState(
    override val emailTextState: TextFieldState = TextFieldState(),
    override val isEmailValid: Boolean = false,
    override val passwordTextState: TextFieldState = TextFieldState(),
    override val isPasswordValid: Boolean = false,
    val canLogin: Boolean = false,
) : AuthTextFieldsState