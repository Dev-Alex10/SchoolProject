package my.schoolProject.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.schoolProject.user.data.UserModel
import my.schoolProject.user.data.UserRepository

class RegisterViewModel(private val repository: UserRepository): ViewModel() {

    fun loadUsers(): LiveData<List<UserModel>> {
        return repository.allUsers
    }

    fun registerUser(name:String,email:String, password: String){
        repository.insert(UserModel(name, email, password))
    }
}
class RegisterViewModelFactory(
    private val repository: UserRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}