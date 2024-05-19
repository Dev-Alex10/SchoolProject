package com.example.quiz.domain.questionAnswer

import com.example.quiz.domain.questionAnswer.model.Answer
import com.example.quiz.domain.questionAnswer.model.Question

interface QuestionAnswerRepository {
    suspend fun getQuestion(id: Long): Question
    suspend fun getAnswer(id: Long): Answer
    suspend fun getQuestions(): List<Question>
}
