package my.schoolProject.user.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import my.schoolProject.student.data.StudentDao
import my.schoolProject.student.data.StudentModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [UserModel::class,StudentModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun studentDao():StudentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "school_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
        val databaseWriterExecutor: ExecutorService = Executors.newFixedThreadPool(2)
    }
}