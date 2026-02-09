package my.schoolproject.core.database.auth.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import my.schoolproject.core.domain.auth.user.User

@Entity(tableName = "User")
data class UserEntity(
    val name: String,
    val email: String,
    @ColumnInfo(name = "photo_url")
    val photoUrl: String?,
    @PrimaryKey
    val uid: String,
)

fun UserEntity.toDomain(): User {
    return User(name, email, photoUrl, uid)
}

fun User.toDatabaseEntity(): UserEntity {
    return UserEntity(name, email, photoUrl, uid)
}
