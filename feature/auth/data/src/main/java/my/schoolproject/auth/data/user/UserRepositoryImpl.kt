package my.schoolproject.auth.data.user

import my.schoolproject.auth.database.dao.UserDao
import my.schoolproject.auth.database.entity.toDatabaseEntity
import my.schoolproject.auth.database.entity.toDomain
import my.schoolproject.auth.domain.user.User
import my.schoolproject.auth.domain.user.UserRepository
import javax.inject.Inject


internal class OfflineUserRepository @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)?.toDomain()


    override suspend fun upsert(user: User) = userDao.upsert(user.toDatabaseEntity())

    override suspend fun deleteAllUsers() = userDao.deleteAll()

}