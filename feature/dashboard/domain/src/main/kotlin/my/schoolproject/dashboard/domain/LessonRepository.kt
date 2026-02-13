package my.schoolproject.dashboard.domain

import my.schoolproject.core.domain.DataError
import my.schoolproject.core.domain.Result
import my.schoolproject.dashboard.domain.model.Answer
import my.schoolproject.dashboard.domain.model.Lesson
import my.schoolproject.dashboard.domain.model.Question

interface LessonRepository {
    suspend fun getQuestions(lessonId: Int): Result<List<Question>, DataError>
    suspend fun getAnswers(questionId: Int): Result<List<Answer>, DataError>
    suspend fun getAllLessons(): Result<List<Lesson>, DataError>

}