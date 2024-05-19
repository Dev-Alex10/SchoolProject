package com.example.quiz.data.model

import com.example.quiz.domain.questionAnswer.model.Question

data class QuestionAPI(
    val id: Long,
    val question: String,
    val answer_id: Long
)

fun QuestionAPI.toDomain(): Question {
    return Question(id, question, answer_id)
}
