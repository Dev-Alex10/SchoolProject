package my.schoolproject.dashboard.data.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable
import my.schoolproject.dashboard.domain.model.Answer

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class AnswerDto(
    val id: Int,
    val text: String,
    val questionIds: List<Int>,
)

fun AnswerDto.toDomain() =
    Answer(id = id, text = text, questionId = questionIds)