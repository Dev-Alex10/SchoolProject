package my.schoolproject.feature.auth.database

import androidx.room.Database
import androidx.room.RoomDatabase
import my.schoolproject.feature.auth.database.dao.UserDao
import my.schoolproject.feature.auth.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}