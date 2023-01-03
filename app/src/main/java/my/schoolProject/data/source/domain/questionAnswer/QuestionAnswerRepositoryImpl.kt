package my.schoolProject.data.source.domain.questionAnswer

import my.schoolProject.data.source.domain.questionAnswer.model.Answer
import my.schoolProject.data.source.domain.questionAnswer.model.Question
import my.schoolProject.data.source.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionAnswerRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    QuestionAnswerRepository {

    override suspend fun getAnswer(id: Long): Answer {
        return remoteDataSource.getAnswer(id)
    }

    override suspend fun getQuestions(): List<Question> {
        return remoteDataSource.getQuestions()
    }

    override suspend fun getQuestion(id: Long): Question {
        return remoteDataSource.getQuestion(id)
    }

}
