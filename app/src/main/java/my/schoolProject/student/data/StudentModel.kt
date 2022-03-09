package my.schoolProject.student.data

import androidx.room.*
import my.schoolProject.user.data.UserModel

@Entity(tableName = "Student")
data class StudentModel(
    @Embedded
    val user: UserModel,
    @ColumnInfo(name = "license_number")
    val licenseNumber: Long,
    @PrimaryKey(autoGenerate = false)
    val number:Long,
//    @ColumnInfo(name = "type")
//    val type:Enum<Type>
)