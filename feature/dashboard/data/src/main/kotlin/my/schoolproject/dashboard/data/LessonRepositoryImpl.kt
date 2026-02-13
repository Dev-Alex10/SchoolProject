package my.schoolproject.dashboard.data

import my.schoolproject.core.domain.DataError
import my.schoolproject.core.domain.Result
import my.schoolproject.dashboard.domain.LessonRepository
import my.schoolproject.dashboard.domain.LessonService
import my.schoolproject.dashboard.domain.model.Answer
import my.schoolproject.dashboard.domain.model.Lesson
import my.schoolproject.dashboard.domain.model.Question
import javax.inject.Inject

internal class LessonRepositoryImpl @Inject constructor(
    private val lessonService: LessonService,
) : LessonRepository {
    override suspend fun getQuestions(lessonId: Int): Result<List<Question>, DataError> {
        return lessonService.getQuestionsForLesson(lessonId)
    }

    override suspend fun getAnswers(questionId: Int): Result<List<Answer>, DataError> {
        return lessonService.getAnswersForQuestion(questionId)
    }

    override suspend fun getAllLessons(): Result<List<Lesson>, DataError> {
        return lessonService.getAllLessons()
    }
}