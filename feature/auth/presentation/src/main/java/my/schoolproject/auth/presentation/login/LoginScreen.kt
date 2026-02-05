package my.schoolproject.auth.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.schoolproject.auth.presentation.R
import my.schoolproject.auth.presentation.components.AuthTopAppBar
import my.schoolproject.auth.presentation.components.UserInput

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val state by loginViewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier.imePadding(),
        topBar = { AuthTopAppBar() }
    ) { padding ->
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UserInput(
                onAuthClick = {
                    loginViewModel.login()
                    //TODO Do this only if login is successful
                    onLoginClick()
                },
                buttonText = stringResource(R.string.feature_auth_presentation_login),
                state = state,
                canSubmit = state.canLogin
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.feature_auth_presentation_do_not_have_account))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.feature_auth_presentation_register),
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        onRegisterClick()
                    }
                )
            }
        }
    }
}