package my.schoolProject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.schoolProject.data.source.domain.user.DefaultUserRepository
import my.schoolProject.data.source.domain.user.UserRepository
import my.schoolProject.data.source.local.UsersLocalSource
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providePhotoRepository(
        localDataSource: UsersLocalSource,
//        remoteDataSource: PhotosRemoteRemoteSource
    ): UserRepository {
        return DefaultUserRepository(localDataSource)
    }
}
