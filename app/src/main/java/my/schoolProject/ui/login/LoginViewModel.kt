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
import my.schoolProject.di.DataStoreManager
import my.schoolProject.utils.common.isValidEmail
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

const val TAGL = "LoginViewModel"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    private val dataStore: DataStoreManager
) : ViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password

    init {
        viewModelScope.launch {
            dataStore.rememberLoginFlow.collect {
                uiState.value = uiState.value.copy(
                    remember = it.rememberLogin
                )
            }
        }
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRememberChange(newValue: Boolean) {
        uiState.value = uiState.value.copy(remember = newValue)
        viewModelScope.launch {
            dataStore.setRememberLoggedIn(uiState.value.remember)
        }
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
            suspendCoroutine<Result<Unit>> {
                accountService.signIn(email, password) { error ->
                    if (error == null) {
                        it.resume(Result.success(Unit))
                    } else {
                        it.resume(Result.failure(error))
                    }
                }
            }.fold(
                onSuccess = {
                    onClick()
                },
                onFailure = {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    Log.e(TAGL, "Error ${it.message}")
                }
            )
        }
    }

    /*   fun onForgotPasswordClick() {
            if (!email.isValidEmail()) {
                SnackbarManager.showMessage(AppText.email_error)
                return
            }

            viewModelScope.launch(showErrorExceptionHandler) {
                accountService.sendRecoveryEmail(email) { error ->
                    if (error != null) onError(error)
                    else SnackbarManager.showMessage(AppText.recovery_email_sent)
                }
            }
        }*/
    override fun onCleared() {
        if (!uiState.value.remember) {
            accountService.signOut()
        }
        super.onCleared()
    }
}
