package my.schoolProject.ui.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import my.schoolProject.R
import my.schoolProject.data.source.remote.accountService.AccountService
import my.schoolProject.utils.common.isValidEmail
import javax.inject.Inject

const val TAGL = "LoginViewModel"

@HiltViewModel
class LoginViewModel @Inject constructor(private val accountService: AccountService) : ViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(onClick: () -> Unit, context: Context) {
        if (!email.isValidEmail()) {
            Toast.makeText(context, R.string.email_error, Toast.LENGTH_LONG).show()
            return
        }

        if (password.isBlank()) {
            Toast.makeText(context, R.string.empty_password_error, Toast.LENGTH_LONG).show()
            return
        }

        viewModelScope.launch {
            accountService.signIn(email, password) { error ->
                if (error == null) {
                    onClick()
                } else {
                    Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
                    Log.e(TAGL, "Error ${error.message}")
                }
            }
        }
    }
//    fun onForgotPasswordClick() {
//        if (!email.isValidEmail()) {
//            SnackbarManager.showMessage(AppText.email_error)
//            return
//        }
//
//        viewModelScope.launch(showErrorExceptionHandler) {
//            accountService.sendRecoveryEmail(email) { error ->
//                if (error != null) onError(error)
//                else SnackbarManager.showMessage(AppText.recovery_email_sent)
//            }
//        }
//    }
}
