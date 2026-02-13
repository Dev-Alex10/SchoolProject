package my.schoolproject.core.database.auth

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import my.schoolproject.core.database.auth.dao.UserDao
import my.schoolproject.core.database.auth.entity.UserEntity

@Database(
    entities = [UserEntity::class], version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    exportSchema = true
)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}