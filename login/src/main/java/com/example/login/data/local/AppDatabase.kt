package com.example.login.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.login.data.student.StudentDao
import com.example.login.data.student.StudentEntity
import com.example.login.data.user.UserDao
import com.example.login.data.user.UserEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [UserEntity::class, StudentEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "school_database"
                ).build()
                INSTANCE = db

                db
            }
        }

        val databaseWriterExecutor: ExecutorService = Executors.newFixedThreadPool(2)
    }
}
