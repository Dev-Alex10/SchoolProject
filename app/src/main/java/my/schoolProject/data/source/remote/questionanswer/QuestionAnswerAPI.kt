package my.schoolProject.data.source.remote.questionanswer

import my.schoolProject.data.source.remote.model.AnswerAPI
import my.schoolProject.data.source.remote.model.QuestionAPI
import retrofit2.http.GET
import retrofit2.http.Path

interface QuestionAnswerAPI {
    @GET("/api/questions")
    suspend fun getQuestions(): List<QuestionAPI>

    @GET("/api/answers")
    suspend fun getAnswers(): List<AnswerAPI>

    @GET("/api/question/{id}")
    suspend fun getQuestion(@Path("id") id: Long): QuestionAPI

    @GET("/api/answer/{id}")
    suspend fun getAnswer(@Path("id") id: Long): AnswerAPI
}
