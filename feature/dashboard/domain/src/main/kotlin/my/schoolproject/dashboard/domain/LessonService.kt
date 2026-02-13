package my.schoolproject.dashboard.domain

import my.schoolproject.core.domain.DataError
import my.schoolproject.core.domain.Result
import my.schoolproject.dashboard.domain.model.Answer
import my.schoolproject.dashboard.domain.model.Lesson
import my.schoolproject.dashboard.domain.model.Question

interface LessonService {
    suspend fun getQuestionsForLesson(lessonId: Int): Result<List<Question>, DataError.Remote>
    suspend fun getAnswersForQuestion(questionId: Int): Result<List<Answer>, DataError.Remote>
    suspend fun getAllLessons(): Result<List<Lesson>, DataError.Remote>
}