package my.schoolproject.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import my.schoolproject.auth.presentation.R
import my.schoolproject.auth.presentation.register.RegisterViewModel

@Composable
fun UserInput(
    onAuthClick: (String, String) -> Unit,
    buttonText: String,
//    sharedAuthViewModel: SharedAuthViewModel,
    registerViewModel: RegisterViewModel,
    canSubmit: () -> Boolean,
    optionalContent: @Composable ((Modifier) -> Unit) = {}
) {
    val email by registerViewModel.email.collectAsStateWithLifecycle()
    val password by registerViewModel.password.collectAsStateWithLifecycle()

    val isPasswordInvalid = password.isNotEmpty() && !registerViewModel.isPasswordValid()

    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val outlineTextFieldModifier = Modifier
        .fillMaxWidth()
        .onFocusEvent { focusState ->
            if (focusState.isFocused) {
                coroutineScope.launch {
                    bringIntoViewRequester.bringIntoView()
                }
            }
        }

    Image(
        painter = painterResource(R.drawable.feature_auth_presentation_computer_image),
        contentDescription = stringResource(R.string.feature_auth_presentation_logo),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(size = 24.dp))
    )
//    Image(
//        painter = painterResource(R.drawable.feature_auth_presentation_computer_image),
//        contentDescription = stringResource(R.string.feature_auth_presentation_logo),
//        modifier = Modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(size = 24.dp)),
//    )
    Spacer(modifier = Modifier.height(32.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = registerViewModel::onEmailTextChange,
            singleLine = true,
            modifier = outlineTextFieldModifier,
            label = { Text(stringResource(R.string.feature_auth_presentation_email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = !registerViewModel.isEmailValid() && email.isNotEmpty(),
            supportingText = {}
        )
        OutlinedTextField(
            value = password,
            onValueChange = registerViewModel::onPasswordTextChange,
            singleLine = true,
            modifier = outlineTextFieldModifier,
            label = { Text(stringResource(R.string.feature_auth_presentation_password)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = isPasswordInvalid,
            supportingText = {
                if (isPasswordInvalid) {
                    Text(text = stringResource(R.string.feature_auth_presentation_password_error))
                }
            }
        )
        optionalContent(outlineTextFieldModifier)
    }
    Button(
        onClick = { onAuthClick(email, password) },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .heightIn(24.dp, 48.dp)
            .bringIntoViewRequester(bringIntoViewRequester),
        enabled = canSubmit()
    ) {
        Text(buttonText)
    }
}