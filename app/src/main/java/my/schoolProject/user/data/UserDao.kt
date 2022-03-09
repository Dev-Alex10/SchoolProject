package my.schoolProject.user.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM User ORDER BY name ASC")
    fun getAlphabetizedUsers():LiveData<List<UserModel>>

    @Query("SELECT * FROM User WHERE email = :email")
    fun getUser(email: String): UserModel

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserModel)


    @Query("DELETE FROM User")
    fun deleteAll()
}