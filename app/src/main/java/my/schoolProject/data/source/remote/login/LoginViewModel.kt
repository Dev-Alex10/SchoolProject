package my.schoolProject.data.source.remote.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.schoolProject.data.source.domain.user.DefaultUserRepository

class LoginViewModel(private val repository: DefaultUserRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResultLiveData = _loginResult

    fun areCredentialsValid(email: String, password: String) {
        viewModelScope.launch {
            val user = repository.getUser(email)
            loginResultLiveData.postValue(user?.password == password)
        }
    }
}

class LoginViewModelFactory(
    private val repository: DefaultUserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
