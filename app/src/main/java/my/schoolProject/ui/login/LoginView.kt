package my.schoolProject.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import my.schoolProject.utils.common.buttonModifier
import my.schoolProject.utils.common.textFieldModifier
import my.schoolProject.utils.composable.BasicButton
import my.schoolProject.utils.composable.BasicToolbar
import my.schoolProject.utils.composable.EmailField
import my.schoolProject.utils.composable.PasswordField
import my.schoolProject.R.string as AppText

@Composable
fun LoginView(
    loginViewModel: LoginViewModel = hiltViewModel(),
    modifier: Modifier,
    onClickCreateAccount: () -> Unit,
    onClickLogin: () -> Unit
) {
    val uiState by loginViewModel.uiState

    BasicToolbar(AppText.login_details)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(
            uiState.email,
            loginViewModel::onEmailChange,
            Modifier
                .textFieldModifier()
        )
        PasswordField(
            uiState.password, loginViewModel::onPasswordChange, Modifier
                .textFieldModifier()
        )
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "Remember login")
            Checkbox(
                checked = uiState.remember,
                onCheckedChange = loginViewModel::onRememberChange
            )
        }

        val context = LocalContext.current
        BasicButton(
            AppText.sign_in,
            Modifier.buttonModifier()
        ) {
            loginViewModel.onSignInClick(onClick = onClickLogin, context)
        }
        BasicButton(
            text = AppText.create_account, modifier = Modifier
                .buttonModifier()
        ) {
            onClickCreateAccount()
            loginViewModel.uiState.value = loginViewModel.uiState.value.copy(
                email = "",
                password = "",
                remember = true
            )
        }
    }
}
