package my.schoolproject.auth_data.local

import androidx.room.Entity
import my.schoolproject.auth_domain.model.User

@Entity(tableName = "User")
data class UserEntity(
    val name: String,
    val email: String,
    val photoUrl: String?,
    val idToken: String,
)

fun UserEntity.toDomain(): User {
    return User(name, email, photoUrl, idToken)
}

fun User.toDatabaseEntity(): UserEntity {
    return UserEntity(name, email, photoUrl, idToken)
}
