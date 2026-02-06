package my.schoolproject.auth_data.user

import my.schoolproject.domain.model.User
import my.schoolproject.domain.user.UserRepository
import my.schoolproject.feature.auth.database.dao.UserDao
import my.schoolproject.feature.auth.database.entity.toDatabaseEntity
import my.schoolproject.feature.auth.database.entity.toDomain
import javax.inject.Inject


internal class OfflineUserRepository @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)?.toDomain()


    override suspend fun insert(user: User) = userDao.insert(user.toDatabaseEntity())

    override suspend fun deleteAllUsers() = userDao.deleteAll()

}