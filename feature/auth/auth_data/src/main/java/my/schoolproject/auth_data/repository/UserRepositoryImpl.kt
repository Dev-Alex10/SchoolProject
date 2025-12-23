package my.schoolproject.auth_data.repository

import my.schoolproject.auth_data.local.UserDao
import my.schoolproject.auth_data.local.toDatabaseEntity
import my.schoolproject.auth_data.local.toDomain
import my.schoolproject.auth_domain.model.User
import my.schoolproject.auth_domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun getUser(email: String) = userDao.getUser(email).toDomain()


    override suspend fun insertUser(user: User) = userDao.insert(user.toDatabaseEntity())

//    override suspend fun deleteAllUsers() = userDao.deleteAll()

}