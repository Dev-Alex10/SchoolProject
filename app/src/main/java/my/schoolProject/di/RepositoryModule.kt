package my.schoolProject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.quiz.questionAnswer.QuestionAnswerRepository
import com.example.quiz.questionAnswer.QuestionAnswerRepositoryImpl
import my.schoolProject.data.source.domain.user.DefaultUserRepository
import com.example.login.domain.user.UserRepository
import com.example.login.data.UsersLocalSource
import com.example.quiz.data.RemoteDataSource
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(
        localDataSource: UsersLocalSource,
//        remoteDataSource: PhotosRemoteRemoteSource
    ): UserRepository {
        return DefaultUserRepository(localDataSource)
    }

    @Singleton
    @Provides
    fun provideQuestionAnswer(
        remoteDataSource: com.example.quiz.data.RemoteDataSource
    ): com.example.quiz.questionAnswer.QuestionAnswerRepository {
        return com.example.quiz.questionAnswer.QuestionAnswerRepositoryImpl(remoteDataSource)
    }
}
