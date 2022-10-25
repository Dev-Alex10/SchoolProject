package my.schoolProject.ui.register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import my.schoolProject.data.source.remote.AccountService
import my.schoolProject.utils.common.isValidEmail
import my.schoolProject.utils.common.isValidPassword
import my.schoolProject.utils.common.snackbar.SnackbarManager
import javax.inject.Inject
import my.schoolProject.R.string as AppText

const val TAG = "RegisterViewModel"

@HiltViewModel
class RegisterViewModel @Inject constructor(private val accountService: AccountService) :
    ViewModel() {
    var uiState = mutableStateOf(RegisterUiState())
        private set
    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    fun onNameChange(newValue: String) {
        uiState.value = uiState.value.copy(name = newValue)
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRegisterUserClick(email: String, password: String, onResult: (Throwable?) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }
        viewModelScope.launch {
            accountService.createAccount(email, password) { error ->
                if (error == null) {
                    //TODO when it's all good
                } else {
                    Log.e(TAG, "Error ${error.message}")
                }
            }
        }
    }
}
