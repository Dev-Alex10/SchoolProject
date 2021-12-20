package my.schoolProject.student.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import my.schoolProject.User.data.UserModel

@Entity(tableName = "Student")
data class StudentModel(
    @Embedded
    val user: UserModel,
    @ColumnInfo(name = "license_number")
    val licenseNumber: Long,
    @PrimaryKey(autoGenerate = false)
    val number:Long,
    @ColumnInfo(name = "type")
    val type:Type
)