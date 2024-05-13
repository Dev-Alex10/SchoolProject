package my.schoolProject.ui.register

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import my.schoolProject.data.source.domain.user.User
import my.schoolProject.data.source.domain.user.UserRepository
import my.schoolProject.data.source.remote.accountService.AccountService
import my.schoolProject.utils.common.isValidEmail
import my.schoolProject.utils.common.isValidPassword
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import my.schoolProject.R.string as AppText


const val TAGR = "RegisterViewModel"

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val accountService: AccountService, private val repository: UserRepository
) : ViewModel() {
    var uiState = mutableStateOf(RegisterUiState())
        private set
    private val email get() = uiState.value.email
    private val password get() = uiState.value.password
    private val name get() = uiState.value.name
    private var token: String? = null
    fun onNameChange(newValue: String) {
        uiState.value = uiState.value.copy(name = newValue)
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }


    fun onRegisterUserClick(onClick: () -> Unit, context: Context) {
        if (!email.isValidEmail()) {
            Toast.makeText(context, AppText.email_error, Toast.LENGTH_LONG).show()
            Log.e(TAGR, "Error email ${AppText.email_error}")
            return
        }

        if (!password.isValidPassword()) {
            Toast.makeText(context, AppText.password_error, Toast.LENGTH_LONG).show()
            Log.e(TAGR, "Error ${AppText.password_error}")
            return
        }
        viewModelScope.launch {
            suspendCoroutine<Result<Unit>> {
                accountService.createAccount(name, email, password) { error ->
                    if (error == null) {
                        Firebase.auth.currentUser?.getIdToken(false)
                            ?.addOnCompleteListener { task ->
                                token = task.result?.token
                                it.resume(Result.success(Unit))
                            }
                    } else {
                        it.resume(Result.failure(error))
                    }
                }
            }.fold(onSuccess = {
                if (!token.isNullOrBlank()) {
                    repository.insertUser(User(name = name, email, token!!))
                    onClick()
                    Log.d("RegisterViewModel", "User created ")
                }
            }, onFailure = {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                Log.e(TAGR, "Error ${it.localizedMessage}")
            })
        }
    }
}
