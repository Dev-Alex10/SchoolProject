package my.schoolProject.data.source.domain.user

import kotlinx.coroutines.flow.Flow
import my.schoolProject.data.source.local.UsersLocalSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultUserRepository @Inject constructor(private val usersLocalSource: UsersLocalSource) :
    UserRepository {
    val allUsers: Flow<List<User>> = usersLocalSource.getAlphabetizedUsers()

    override fun getUser(email: String): User {
        return usersLocalSource.getUser(email)
    }

    override suspend fun insertUser(user: User) {
        usersLocalSource.insert(user)
    }

    override fun deleteAllUsers() {
        usersLocalSource.deleteAll()
    }

}
