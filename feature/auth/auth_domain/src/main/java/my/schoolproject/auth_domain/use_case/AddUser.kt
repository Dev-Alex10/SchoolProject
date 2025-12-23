package my.schoolproject.auth_domain.use_case

import my.schoolproject.auth_domain.model.User
import my.schoolproject.auth_domain.repository.UserRepository

class AddUser (private val repository: UserRepository) {
    suspend operator fun invoke(user: User){
        repository.insertUser(user)
    }
}