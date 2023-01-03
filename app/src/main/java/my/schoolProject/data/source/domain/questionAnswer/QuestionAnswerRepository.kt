package my.schoolProject.data.source.domain.questionAnswer

import my.schoolProject.data.source.domain.questionAnswer.model.Answer
import my.schoolProject.data.source.domain.questionAnswer.model.Question

interface QuestionAnswerRepository {
    suspend fun getQuestion(id: Long): Question
    suspend fun getAnswer(id: Long): Answer
    suspend fun getQuestions(): List<Question>
}
