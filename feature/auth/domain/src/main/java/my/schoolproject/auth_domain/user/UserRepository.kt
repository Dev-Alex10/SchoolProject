package my.schoolproject.auth_domain.user

import my.schoolproject.auth_domain.model.User

interface UserRepository {
    suspend fun getUserByEmail(email: String): User?
    suspend fun insert(user: User)
    suspend fun deleteAllUsers()
}