package my.schoolProject.data.source.domain.questionAnswer

import my.schoolProject.data.source.domain.questionAnswer.model.Answer
import my.schoolProject.data.source.domain.questionAnswer.model.Question
import my.schoolProject.data.source.domain.questionAnswer.model.QuestionAnswer

interface QuestionAnswerRepository {
    suspend fun getQuestionAnswer(questionId: Long, answerId: Long): QuestionAnswer
    suspend fun getQuestion(id: Long): Question
    suspend fun getAnswer(id: Long): Answer
}
