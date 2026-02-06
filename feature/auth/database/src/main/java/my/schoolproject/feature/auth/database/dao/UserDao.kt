package my.schoolproject.feature.auth.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import my.schoolproject.feature.auth.database.entity.UserEntity

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM User ORDER BY name ASC")
    fun getAlphabetizedUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM User WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)


    @Query("DELETE FROM User")
    suspend fun deleteAll()
}