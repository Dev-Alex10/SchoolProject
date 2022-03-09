package my.schoolProject.user.data

import androidx.lifecycle.LiveData

class UserRepository (private val userDao: UserDao) {
    val allUsers: LiveData<List<UserModel>> = userDao.getAlphabetizedUsers()
    var user : UserModel = UserModel("","","")
    fun insert(user: UserModel) {
        AppDatabase
            .databaseWriterExecutor
            .execute {
                userDao.insert(user)
            }
    }
    fun getUser(email:String): UserModel {
        AppDatabase
            .databaseWriterExecutor
            .execute {
               user = userDao.getUser(email)
            }
        return user
    }
}