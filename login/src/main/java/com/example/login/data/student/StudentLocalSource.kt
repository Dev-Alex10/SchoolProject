package com.example.login.data.student

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.login.domain.student.Student
import javax.inject.Inject

class StudentLocalSource @Inject constructor(private val studentDao: StudentDao) {

    fun getStudents(): Flow<List<Student>> {
        return studentDao.getStudents().map { dbStudents ->
            dbStudents.map {
                it.toDomain()
            }
        }
    }

    fun insertStudent(student: Student) {
        studentDao.insert(student.toDatabaseEntity())
    }
}
