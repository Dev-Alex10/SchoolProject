package my.schoolproject.auth.presentation.register

import androidx.compose.foundation.text.input.TextFieldState

data class RegisterState(
    val emailTextState: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = false,
    val passwordTextState: TextFieldState = TextFieldState(),
    val isPasswordValid: Boolean = false,
    val confirmPasswordTextState: TextFieldState = TextFieldState(),
    val isConfirmPasswordValid: Boolean = false,
    val canRegister: Boolean = false
)
