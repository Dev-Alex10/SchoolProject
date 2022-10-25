package my.schoolProject.data.source.domain.student

interface StudentRepository {
    fun insertStudent(student: Student)
}
