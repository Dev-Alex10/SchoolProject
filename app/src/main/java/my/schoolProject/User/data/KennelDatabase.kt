package my.schoolProject.User.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class KennelDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: KennelDatabase? = null

        fun getDatabase(context: Context): KennelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KennelDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }

        val databaseWriterExecutor: ExecutorService = Executors.newFixedThreadPool(2)
    }
}