package com.example.login.domain.user

import my.schoolProject.data.source.domain.user.User

interface UserRepository {
    fun getUser(email: String): User
    suspend fun insertUser(user: User)
    fun deleteAllUsers()
}
