package com.example.quiz.data.model

import com.example.quiz.domain.questionAnswer.model.Answer


data class AnswerAPI(
    val id: Long,
    val answer: String
)

fun AnswerAPI.toDomain(): Answer {
    return Answer(id, answer)
}
