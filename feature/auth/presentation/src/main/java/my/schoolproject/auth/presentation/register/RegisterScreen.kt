package my.schoolproject.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import my.schoolproject.auth.presentation.components.UserInput

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel
) {
    Scaffold { padding ->
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UserInput(
                onAuthClick = { _, _ ->

                },
                buttonText = "Register",
                canSubmit = { registerViewModel.areCredentialsValid() },
                registerViewModel = registerViewModel,
//            optionalContent = { outlinedTextFieldModifier ->
//                OutlinedTextField(
//                    value = confirmPassword,
//                    onValueChange = registerViewModel::onConfirmPasswordTextChange,
//                    singleLine = true,
//                    modifier = outlinedTextFieldModifier,
//                    label = { Text(stringResource(Resources.String.confirm_password)) },
//                    visualTransformation = PasswordVisualTransformation(),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                    isError = isError,
//                    supportingText = {
//                        if (isError) {
//                            Text(text = stringResource(Resources.String.confirm_password_error))
//                        }
//                    }
//                )
//            }
            )
        }
    }
}

@PreviewLightDark
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(registerViewModel = hiltViewModel())
}