package my.schoolproject.dashboard.data.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable
import my.schoolproject.dashboard.domain.model.Lesson

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class LessonDto(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String?,
)

fun LessonDto.toDomain() =
    Lesson(id = id, name = title, description = description, imageUrl = imageUrl)