package my.schoolProject.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.quiz.data.remote.questionanswer.QuestionAnswerAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://192.168.1.80:3000"

    @Provides
    fun provideQuestionAnswers(): com.example.quiz.data.remote.questionanswer.QuestionAnswerAPI {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                )
            )
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(
                        1,
                        TimeUnit.SECONDS
                    ).build()
            )
            .build()
            .create(com.example.quiz.data.remote.questionanswer.QuestionAnswerAPI::class.java)
    }
}
