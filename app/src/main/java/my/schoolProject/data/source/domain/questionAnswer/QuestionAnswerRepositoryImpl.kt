package my.schoolProject.data.source.domain.questionAnswer

import my.schoolProject.data.source.domain.questionAnswer.model.Answer
import my.schoolProject.data.source.domain.questionAnswer.model.Question
import my.schoolProject.data.source.domain.questionAnswer.model.QuestionAnswer
import my.schoolProject.data.source.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionAnswerRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    QuestionAnswerRepository {
    override suspend fun getQuestionAnswer(questionId: Long, answerId: Long): QuestionAnswer {
//        return remoteDataSource.getQuestionAnswer()
        val question = getQuestion(questionId)
        val answer = getAnswer(answerId)
        return QuestionAnswer(question = question.question, answer.answer)
    }

    override suspend fun getAnswer(id: Long): Answer {
        return remoteDataSource.getAnswer(id)
    }

    override suspend fun getQuestion(id: Long): Question {
        return remoteDataSource.getQuestion(id)
    }

}
