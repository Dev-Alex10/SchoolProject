package com.example.login.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import my.schoolProject.data.source.domain.user.User
import com.example.login.data.user.UserDao
import com.example.login.data.user.toDatabaseEntity
import com.example.login.data.user.toDomain
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
