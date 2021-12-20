package my.schoolProject.student.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDao {
    @Query("SELECT * FROM Student")
    fun getStudents(): LiveData<List<StudentModel>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(student: StudentModel)

    @Query("DELETE FROM User")
    fun deleteAll()
}