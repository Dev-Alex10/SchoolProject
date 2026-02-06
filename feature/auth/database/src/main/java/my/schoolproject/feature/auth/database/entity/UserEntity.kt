package my.schoolproject.feature.auth.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import my.schoolproject.auth_domain.model.User

@Entity(tableName = "User")
data class UserEntity(
    val name: String,
    @PrimaryKey
    val email: String,
    @ColumnInfo(name = "photo_url")
    val photoUrl: String?,
    @ColumnInfo(name = "id_token")
    val idToken: String,
)

fun UserEntity.toDomain(): User {
    return User(name, email, photoUrl, idToken)
}

fun User.toDatabaseEntity(): UserEntity {
    return UserEntity(name, email, photoUrl, idToken)
}
