package my.schoolProject.data.source.domain.student

import com.example.login.domain.student.Student
import my.schoolProject.data.source.local.student.StudentLocalSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepositoryImpl @Inject constructor(private val studentLocalSource: StudentLocalSource) :
    StudentRepository {
    val allStudents = studentLocalSource.getStudents()

    override fun insertStudent(student: Student) {
        studentLocalSource.insertStudent(student)
    }

}
