package my.schoolproject.auth_domain.repository

import my.schoolproject.auth_domain.model.User

interface UserRepository {
    suspend fun getUser(email: String): User
    suspend fun insertUser(user: User)
//    fun deleteAllUsers()
}