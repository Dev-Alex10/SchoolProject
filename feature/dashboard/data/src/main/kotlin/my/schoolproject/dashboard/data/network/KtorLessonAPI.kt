package my.schoolproject.dashboard.data.network

import ApiResponse
import my.schoolproject.dashboard.data.dto.AnswerDto
import my.schoolproject.dashboard.data.dto.LessonDto
import my.schoolproject.dashboard.data.dto.QuestionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface KtorLessonAPI {
    @GET("/lessons")
    suspend fun getAllLessons(): Response<ApiResponse<LessonDto>>

    @GET("/{questionId}/answers")
    suspend fun getAnswersForQuestion(@Path("questionId") questionId: Int): Response<ApiResponse<AnswerDto>>


    @GET("/lessons/{lessonId}/questions")
    suspend fun getQuestionsForLesson(@Path("lessonId") lessonId: Int): Response<ApiResponse<QuestionDto>>
}