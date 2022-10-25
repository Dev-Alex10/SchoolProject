package my.schoolProject.data.source.domain.user

interface UserRepository {
    fun getUser(email: String): User
    fun insertUser(user: User)
}
