package my.schoolproject.domain.user

import my.schoolproject.domain.model.User

interface UserRepository {
    suspend fun getUserByEmail(email: String): User?
    suspend fun insert(user: User)
    suspend fun deleteAllUsers()
}