package my.schoolProject.data.source.local.user

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM User ORDER BY name ASC")
    fun getAlphabetizedUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM User WHERE email = :email")
    fun getUser(email: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)


    @Query("DELETE FROM User")
    fun deleteAll()
}
