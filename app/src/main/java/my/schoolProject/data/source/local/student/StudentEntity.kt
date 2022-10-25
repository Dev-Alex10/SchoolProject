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
    val internalId: Long,
    @ColumnInfo(name = "license_number")
    val licenseNumber: Long,
    @ColumnInfo(name = "type")
    val licenceType: LicenceType
)

fun StudentEntity.toDomain(): Student {
    return Student(
        name = user.name,
        email = user.email,
        password = user.password,
        internalId = internalId,
        licenseId = licenseNumber,
        licenseType = licenceType
    )
}

fun Student.toDatabaseEntity(): StudentEntity {
    return StudentEntity(
        UserEntity(name = name, email = email, password = password),
        internalId = internalId,
        licenseNumber = licenseId,
        licenceType = licenseType
    )
}

enum class LicenceType {
    A,
    A1,
    A2,
    B,
    BE,
    C,
    CE,
    D,
    DE
}
