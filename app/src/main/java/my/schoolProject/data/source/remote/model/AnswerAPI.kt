package my.schoolProject.data.source.remote.model

import my.schoolProject.data.source.domain.questionAnswer.model.Answer

data class AnswerAPI(
    val id: Long,
    val answer: String
)

fun AnswerAPI.toDomain(): Answer {
    return Answer(id, answer)
}
