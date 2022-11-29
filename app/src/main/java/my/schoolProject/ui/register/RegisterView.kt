package my.schoolProject.ui.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import my.schoolProject.utils.common.buttonModifier
import my.schoolProject.utils.common.textFieldModifier
import my.schoolProject.utils.composable.*
import my.schoolProject.R.string as AppText

@Composable
fun RegisterView(
    viewModel: RegisterViewModel = hiltViewModel(),
    modifier: Modifier,
    onClickRegister: () -> Unit
) {
    val uiState by viewModel.uiState
    BasicToolbar(AppText.create_account)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NameField(
            uiState.name, viewModel::onNameChange, Modifier
                .textFieldModifier()
        )
        EmailField(
            uiState.email, viewModel::onEmailChange, Modifier
                .textFieldModifier()
        )
        PasswordField(
            uiState.password, viewModel::onPasswordChange, Modifier
                .textFieldModifier()
        )
        val context = LocalContext.current
        BasicButton(
            AppText.create_account, Modifier
                .buttonModifier()
        ) {
            viewModel.onRegisterUserClick(onClickRegister, context)
        }
    }
}
