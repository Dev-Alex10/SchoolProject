package my.schoolproject.core.domain.auth.user

import kotlinx.coroutines.flow.Flow


interface UserRepository {
    fun getCurrentUser(): Flow<User>
    suspend fun getUserByEmail(email: String): User?
    suspend fun updateUser(user: User)
    suspend fun deleteAllUsers()
}