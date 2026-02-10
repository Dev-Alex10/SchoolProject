package my.schoolproject.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.schoolproject.auth.presentation.R
import my.schoolproject.auth.presentation.components.AuthTopAppBar
import my.schoolproject.auth.presentation.components.UserInput
import my.schoolproject.core.presentation.util.ObserveAsEvents

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onSuccessfulRegister: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarState = remember { SnackbarHostState() }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is RegisterEvent.OnError -> snackbarState.showSnackbar(event.error)
            RegisterEvent.OnSuccess -> onSuccessfulRegister()
        }
    }
    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            AuthTopAppBar(
                modifier = Modifier.padding(8.dp),
                onBackClick = onBackClick
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarState)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UserInput(
                onAuthClick = viewModel::register,
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
    RegisterScreen(
        onBackClick = {},
        onSuccessfulRegister = {}
    )
}