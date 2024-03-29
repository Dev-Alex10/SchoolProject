package my.schoolProject.data.source.domain.user

interface UserRepository {
    fun getUser(email: String): User
    suspend fun insertUser(user: User)
    fun deleteAllUsers()
}
