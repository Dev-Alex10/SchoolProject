package com.example.quiz.data.questionanswer

import com.example.quiz.data.model.AnswerAPI
import com.example.quiz.data.remote.model.AnswerAPI
import com.example.quiz.data.model.QuestionAPI
import retrofit2.http.GET
import retrofit2.http.Path

interface QuestionAnswerAPI {
    @GET("/api/questions")
    suspend fun getQuestions(): List<QuestionAPI>

    @GET("/api/answers")
    suspend fun getAnswers(): List<AnswerAPI>

    @GET("/api/questions/{id}")
    suspend fun getQuestion(@Path("id") id: Long): QuestionAPI

    @GET("/api/answers/{id}")
    suspend fun getAnswer(@Path("id") id: Long): AnswerAPI
}
