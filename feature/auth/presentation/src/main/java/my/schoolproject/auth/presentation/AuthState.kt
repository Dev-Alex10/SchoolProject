package my.schoolproject.auth.presentation

import androidx.compose.foundation.text.input.TextFieldState

interface AuthTextFieldsState {
    val emailTextState: TextFieldState
    val passwordTextState: TextFieldState
    val isEmailValid: Boolean
    val isPasswordValid: Boolean
}