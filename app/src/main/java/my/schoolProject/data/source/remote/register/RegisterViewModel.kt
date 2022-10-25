package my.schoolProject.data.source.remote.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.schoolProject.data.source.local.user.UserEntity
import my.schoolProject.data.source.domain.user.DefaultUserRepository

class RegisterViewModel(private val repository: DefaultUserRepository): ViewModel() {

    fun loadUsers(): LiveData<List<UserEntity>> {
        return repository.allUsers
    }

    fun registerUser(name:String,email:String, password: String){
        repository.insert(UserEntity(name, email, password))
    }
}
class RegisterViewModelFactory(
    private val repository: DefaultUserRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
