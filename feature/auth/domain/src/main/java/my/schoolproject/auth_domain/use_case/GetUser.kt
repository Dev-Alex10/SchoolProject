package my.schoolproject.auth_domain.use_case

import my.schoolproject.auth_domain.repository.UserRepository

class GetUser(private val repository: UserRepository) {
    suspend operator fun invoke(email: String){
        repository.getUser(email)
    }
}