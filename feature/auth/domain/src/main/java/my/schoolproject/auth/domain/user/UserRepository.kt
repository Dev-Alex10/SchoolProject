package my.schoolproject.auth.domain.user

interface UserRepository {
    suspend fun getUserByEmail(email: String): User?
    suspend fun upsert(user: User)
    suspend fun deleteAllUsers()
}