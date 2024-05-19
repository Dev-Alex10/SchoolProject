package com.example.login.data.student

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.login.domain.student.Student
import com.example.login.data.user.UserEntity

@Entity(tableName = "Student")
data class StudentEntity(
    @Embedded
    val user: UserEntity,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "internalId")
    val internalId: Long
)

fun StudentEntity.toDomain(): Student {
    return Student(
        name = user.name,
        email = user.email,
        password = user.idToken,
        internalId = internalId
    )
}

fun Student.toDatabaseEntity(): StudentEntity {
    return StudentEntity(
        UserEntity(name = name, email = email, idToken = password),
        internalId = internalId
    )
}
