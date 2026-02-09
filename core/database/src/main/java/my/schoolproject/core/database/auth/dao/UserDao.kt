package my.schoolproject.core.database.auth.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import my.schoolproject.core.database.auth.entity.UserEntity

@Dao
interface UserDao {
    @Transaction
    @Query("SELECT * FROM User ORDER BY name ASC")
    fun getAlphabetizedUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM User WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Upsert
    suspend fun upsert(user: UserEntity)


    @Query("DELETE FROM User")
    suspend fun deleteAll()
}
