package my.schoolproject.core.data.auth.user

import my.schoolproject.core.database.auth.dao.UserDao
import my.schoolproject.core.database.auth.entity.toDatabaseEntity
import my.schoolproject.core.database.auth.entity.toDomain
import my.schoolproject.core.domain.auth.user.User
import my.schoolproject.core.domain.auth.user.UserRepository
import javax.inject.Inject


internal class OfflineUserRepository @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)?.toDomain()


    override suspend fun upsert(user: User) = userDao.upsert(user.toDatabaseEntity())

    override suspend fun deleteAllUsers() = userDao.deleteAll()

}