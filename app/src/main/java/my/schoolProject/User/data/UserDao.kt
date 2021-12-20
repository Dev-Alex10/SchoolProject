package my.schoolProject.User.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM User ORDER BY name ASC")
    fun getAlphabetizedUsers():LiveData<List<UserModel>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(user: UserModel)

    @Query("DELETE FROM User")
    fun deleteAll()
}