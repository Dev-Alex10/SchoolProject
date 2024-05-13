package my.schoolProject.data.source.local.student

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import my.schoolProject.data.source.domain.student.Student
import my.schoolProject.data.source.local.user.UserEntity

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
