package my.schoolproject.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.schoolproject.auth.presentation.R
import my.schoolproject.auth.presentation.components.UserInput

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel
) {
    val state by registerViewModel.state.collectAsStateWithLifecycle()
    Scaffold { padding ->
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UserInput(
                onAuthClick = registerViewModel::register,
                buttonText = stringResource(R.string.feature_auth_presentation_register),
                canSubmit = state.canRegister,
                state = state,
                confirmPasswordTextField = { outlinedTextFieldModifier ->
                    val isError =
                        !state.isConfirmPasswordValid && state.confirmPasswordTextState.text.isNotEmpty()

                    OutlinedSecureTextField(
                        state = state.confirmPasswordTextState,
                        modifier = outlinedTextFieldModifier,
                        label = { Text(stringResource(R.string.feature_auth_presentation_confirm_password)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        isError = isError,
                        supportingText = {
                            if (isError) {
                                Text(text = stringResource(R.string.feature_auth_presentation_confirm_password_error))
                            }
                        }
                    )
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(registerViewModel = hiltViewModel())
}