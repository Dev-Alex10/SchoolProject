package my.schoolproject.core.domain.auth.user

interface UserRepository {
    suspend fun getUserByEmail(email: String): User?
    suspend fun upsert(user: User)
    suspend fun deleteAllUsers()
}