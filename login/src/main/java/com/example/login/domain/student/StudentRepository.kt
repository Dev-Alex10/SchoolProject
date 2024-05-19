package my.schoolProject.data.source.domain.student

import com.example.login.domain.student.Student

interface StudentRepository {
    fun insertStudent(student: Student)
}
