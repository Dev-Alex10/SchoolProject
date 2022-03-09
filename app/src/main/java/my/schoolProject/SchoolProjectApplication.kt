package my.schoolProject

import android.app.Application
import my.schoolProject.user.data.AppDatabase
import my.schoolProject.user.data.UserRepository

class SchoolProjectApplication : Application() {

    val database by lazy { AppDatabase.getDatabase(this) }

    val repository by lazy { UserRepository(database.userDao()) }
}