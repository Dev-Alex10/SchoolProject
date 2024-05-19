package com.example.login.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import my.schoolProject.data.source.domain.user.User

@Entity(tableName = "User")
data class UserEntity(
    @ColumnInfo(name = "name")
    val name: String,
    @PrimaryKey(autoGenerate = false)
    val email: String,
    @ColumnInfo(name = "idToken")
    val idToken: String
)

fun UserEntity.toDomain(): User {
    return User(name, email, idToken)
}

fun User.toDatabaseEntity(): UserEntity {
    return UserEntity(name, email, idToken)
}
