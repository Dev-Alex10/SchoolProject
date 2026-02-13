package my.schoolproject.dashboard.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.schoolproject.dashboard.data.network.KtorLessonAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://192.168.1.78:9090"

    @Provides
    @Singleton
    fun provideLessonAPI(): KtorLessonAPI {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(
                        1,
                        TimeUnit.SECONDS
                    ).build()
            )
            .build()
            .create(KtorLessonAPI::class.java)
    }
}