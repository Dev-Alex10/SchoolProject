package my.schoolProject.data.source.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import my.schoolProject.data.source.domain.user.User
import my.schoolProject.data.source.local.user.UserDao
import my.schoolProject.data.source.local.user.toDatabaseEntity
import my.schoolProject.data.source.local.user.toDomain
import javax.inject.Inject

class UsersLocalSource @Inject constructor(private val userDao: UserDao) {
    fun getAlphabetizedUsers(): Flow<List<User>> {
        return userDao.getAlphabetizedUsers().map { dbUsers ->
            dbUsers.map { it.toDomain() }
        }
    }


    fun getUser(email: String): User {
        return userDao.getUser(email = email).toDomain()
    }


    suspend fun insert(user: User) {
        userDao.insert(user.toDatabaseEntity())
    }


    fun deleteAll() {
        userDao.deleteAll()
    }
}
