package my.schoolproject.auth.database

import androidx.room.Database
import androidx.room.RoomDatabase
import my.schoolproject.auth.database.dao.UserDao
import my.schoolproject.auth.database.entity.UserEntity

@Database(
    entities = [UserEntity::class], version = 1,
    autoMigrations = [
    ],
    exportSchema = true
)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}