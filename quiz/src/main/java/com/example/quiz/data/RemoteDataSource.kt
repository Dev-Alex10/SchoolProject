package com.example.quiz.data

import com.example.quiz.data.model.toDomain
import com.example.quiz.data.questionanswer.QuestionAnswerAPI
import com.example.quiz.domain.questionAnswer.model.Answer
import com.example.quiz.domain.questionAnswer.model.Question
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val questionAnswerAPI: QuestionAnswerAPI) {
    suspend fun getQuestions(): List<Question> {
        return questionAnswerAPI.getQuestions().map {
            it.toDomain()
        }
    }

    suspend fun getAnswers(): List<Answer> {
        return questionAnswerAPI.getAnswers().map {
            it.toDomain()
        }
    }

    suspend fun getQuestion(id: Long): Question {
        return questionAnswerAPI.getQuestion(id).toDomain()
    }

    suspend fun getAnswer(id: Long): Answer {
        return questionAnswerAPI.getAnswer(id).toDomain()
    }

}
