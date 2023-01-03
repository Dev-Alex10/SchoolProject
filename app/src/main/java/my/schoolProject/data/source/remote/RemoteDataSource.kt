package my.schoolProject.data.source.remote

import my.schoolProject.data.source.domain.questionAnswer.model.Answer
import my.schoolProject.data.source.domain.questionAnswer.model.Question
import my.schoolProject.data.source.remote.model.toDomain
import my.schoolProject.data.source.remote.questionanswer.QuestionAnswerAPI
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
