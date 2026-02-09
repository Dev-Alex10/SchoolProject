package my.schoolproject.auth.presentation.register

import androidx.compose.foundation.text.input.TextFieldState
import my.schoolproject.auth.presentation.AuthTextFieldsState

data class RegisterState(
    override val emailTextState: TextFieldState = TextFieldState(),
    override val isEmailValid: Boolean = false,
    override val passwordTextState: TextFieldState = TextFieldState(),
    override val isPasswordValid: Boolean = false,
    val confirmPasswordTextState: TextFieldState = TextFieldState(),
    val isConfirmPasswordValid: Boolean = false,
    val canRegister: Boolean = false
) : AuthTextFieldsState