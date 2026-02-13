package my.schoolproject.dashboard.presentation.model

import my.schoolproject.dashboard.domain.model.Answer

data class AnswerUi(
    val id: Int,
    val text: String,
    val questionId: Int,
    val isCorrect: Boolean,
)

fun AnswerUi.toDomain() = Answer(
    id = id,
    text = text,
    questionId = listOf(questionId),
)

fun Answer.toUi() = AnswerUi(
    id = id,
    text = text,
    questionId = questionId.first(),
    isCorrect = false,
)