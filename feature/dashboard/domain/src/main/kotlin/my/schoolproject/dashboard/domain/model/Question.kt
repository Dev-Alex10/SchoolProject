package my.schoolproject.dashboard.domain.model

data class Question(
    val id: Int,
    val text: String,
    val answers: List<Answer>,
    val lessonId: List<Int>,
    val correctAnswerId: Int,
)
