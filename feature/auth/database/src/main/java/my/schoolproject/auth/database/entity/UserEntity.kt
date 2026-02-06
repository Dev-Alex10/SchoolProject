package my.schoolproject.auth.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import my.schoolproject.auth.domain.user.User

@Entity(tableName = "User")
data class UserEntity(
    val name: String,
    @PrimaryKey
    val email: String,
    @ColumnInfo(name = "photo_url")
    val photoUrl: String?,
    val uid: String,
)

fun UserEntity.toDomain(): User {
    return User(name, email, photoUrl, uid)
}

fun User.toDatabaseEntity(): UserEntity {
    return UserEntity(name, email, photoUrl, uid)
}
