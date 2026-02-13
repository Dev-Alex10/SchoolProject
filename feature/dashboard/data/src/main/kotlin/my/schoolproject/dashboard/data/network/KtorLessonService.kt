package my.schoolproject.dashboard.data.network

import android.util.Log
import com.google.gson.JsonParseException
import my.schoolproject.core.domain.DataError
import my.schoolproject.core.domain.Result
import my.schoolproject.core.domain.map
import my.schoolproject.dashboard.data.dto.toDomain
import my.schoolproject.dashboard.domain.LessonService
import my.schoolproject.dashboard.domain.model.Answer
import my.schoolproject.dashboard.domain.model.Lesson
import my.schoolproject.dashboard.domain.model.Question
import retrofit2.Response
import java.net.ConnectException
import javax.inject.Inject

class KtorLessonService @Inject constructor(
    private val ktorLessonAPI: KtorLessonAPI
) : LessonService {
    override suspend fun getQuestionsForLesson(lessonId: Int): Result<List<Question>, DataError.Remote> {
        val response = ktorLessonAPI.getQuestionsForLesson(lessonId)
        return responseToResult(response).map { it.valuesList.map { questionDto -> questionDto.toDomain() } }
    }

    override suspend fun getAnswersForQuestion(questionId: Int): Result<List<Answer>, DataError.Remote> {
        val response = ktorLessonAPI.getAnswersForQuestion(questionId)
        return responseToResult(response).map { it.valuesList.map { answerDto -> answerDto.toDomain() } }
    }

    override suspend fun getAllLessons(): Result<List<Lesson>, DataError.Remote> {
        try {
            val response = ktorLessonAPI.getAllLessons()
            println("RETROFIT RESPONSE: $response")
            val result =
                responseToResult(response).map { it.valuesList.map { lessonDto -> lessonDto.toDomain() } }
            println("RETROFIT Result: $result")
            return result
        } catch (e: ConnectException) {
            Log.e(this::class.simpleName, "getAllLessons: ${e.message}")
            return Result.Failure(DataError.Remote.NO_INTERNET)
        }
    }

    inline fun <reified T> responseToResult(response: Response<T>): Result<T, DataError.Remote> {
        if (response.isSuccessful) {
            return try {
                Result.Success(response.body()!!)
            } catch (e: JsonParseException) {
                Log.e(this::class.simpleName, "responseToResult: ${e.message}")
                Result.Failure(DataError.Remote.SERIALIZATION)
            }
        }
        return when (response.code()) {
            400 -> Result.Failure(DataError.Remote.BAD_REQUEST)
            401 -> Result.Failure(DataError.Remote.UNAUTHORIZED)
            403 -> Result.Failure(DataError.Remote.FORBIDDEN)
            404 -> Result.Failure(DataError.Remote.NOT_FOUND)
            408 -> Result.Failure(DataError.Remote.REQUEST_TIMEOUT)
            409 -> Result.Failure(DataError.Remote.CONFLICT)
            413 -> Result.Failure(DataError.Remote.PAYLOAD_TOO_LARGE)
            429 -> Result.Failure(DataError.Remote.TOO_MANY_REQUESTS)
            500 -> Result.Failure(DataError.Remote.SERVER_ERROR)
            503 -> Result.Failure(DataError.Remote.SERVICE_UNAVAILABLE)
            else -> Result.Failure(DataError.Remote.UNKNOWN)
        }
    }

}