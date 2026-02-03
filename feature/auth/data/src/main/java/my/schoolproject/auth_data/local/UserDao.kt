package my.schoolproject.auth_data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM User ORDER BY name ASC")
    suspend fun getAlphabetizedUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM User WHERE email = :email")
    suspend fun getUser(email: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)


    @Query("DELETE FROM User")
    suspend fun deleteAll()
}