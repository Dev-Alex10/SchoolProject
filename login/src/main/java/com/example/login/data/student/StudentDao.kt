package com.example.login.data.student

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM Student")
    fun getStudents(): Flow<List<StudentEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(student: StudentEntity)

    @Query("DELETE FROM User")
    fun deleteAll()
}
