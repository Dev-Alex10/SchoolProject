package my.schoolProject.data.source.remote.model

import my.schoolProject.data.source.domain.questionAnswer.model.Question

data class QuestionAPI(
    val id: Long,
    val question: String,
    val answer_id: Long
)

fun QuestionAPI.toDomain(): Question {
    return Question(id, question, answer_id)
}
