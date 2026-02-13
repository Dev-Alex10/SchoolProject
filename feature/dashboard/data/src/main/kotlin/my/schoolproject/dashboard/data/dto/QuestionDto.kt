package my.schoolproject.dashboard.data.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable
import my.schoolproject.dashboard.domain.model.Question

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class QuestionDto(
    val id: Int,
    val text: String,
    val answers: List<AnswerDto>,
    val moduleId: List<Int>,
    val correctAnswerId: Int,
)

fun QuestionDto.toDomain() =
    Question(
        id = id,
        lessonId = moduleId,
        text = text,
        answers = answers.map { it.toDomain() },
        correctAnswerId = correctAnswerId
    )