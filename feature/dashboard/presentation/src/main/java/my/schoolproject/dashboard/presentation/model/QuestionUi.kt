package my.schoolproject.dashboard.presentation.model

import my.schoolproject.dashboard.domain.model.Question

data class QuestionUi(
    val id: Int,
    val question: String,
    val answers: List<AnswerUi>,
    val lessonId: Int,
    val correctAnswerId: Int
)

fun QuestionUi.toDomain() = Question(
    id = id,
    text = question,
    answers = answers.map { it.toDomain() },
    lessonId = listOf(lessonId),
    correctAnswerId = correctAnswerId,
)

fun Question.toUi() = QuestionUi(
    id = id,
    question = text,
    answers = answers.map { it.toUi() },
    lessonId = lessonId.first(),
    correctAnswerId
)