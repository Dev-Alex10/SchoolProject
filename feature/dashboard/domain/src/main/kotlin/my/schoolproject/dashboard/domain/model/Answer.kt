package my.schoolproject.dashboard.domain.model

data class Answer(
    val id: Int,
    val text: String,
    val questionId: List<Int>,
)
