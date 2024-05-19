package my.schoolProject.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.login.data.local.AppDatabase
import com.example.login.data.student.StudentDao
import com.example.login.data.user.UserDao
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideStudentDao(database: AppDatabase): StudentDao {
        return database.studentDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        val passphrase = SQLiteDatabase.getBytes("PassPhrase".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "school_database"
        )
//            .openHelperFactory(factory) //encryption of all the database
            .build()
    }
}
