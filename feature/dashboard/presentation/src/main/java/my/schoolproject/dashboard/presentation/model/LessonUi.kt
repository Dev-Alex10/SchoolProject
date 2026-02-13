package my.schoolproject.dashboard.presentation.model

import my.schoolproject.dashboard.domain.model.Lesson

data class LessonUi(
    val id: Int,
    val title: String,
    val description: String,
    val questions: List<QuestionUi>,
    val imageUrl: String?
)

fun LessonUi.toDomain() = Lesson(
    id = id,
    name = title,
    description = description,
    imageUrl = imageUrl
)

fun Lesson.toUi() = LessonUi(
    id = id,
    title = name,
    description = description,
    questions = emptyList(),
    imageUrl = imageUrl
)