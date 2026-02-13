package my.schoolproject.dashboard.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.schoolproject.dashboard.data.LessonRepositoryImpl
import my.schoolproject.dashboard.data.network.KtorLessonService
import my.schoolproject.dashboard.domain.LessonRepository
import my.schoolproject.dashboard.domain.LessonService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LessonDataModule {
    @Binds
    @Singleton
    internal abstract fun bindsLessonRepository(lessonRepository: LessonRepositoryImpl): LessonRepository

    @Binds
    @Singleton
    internal abstract fun bindsLessonService(lessonService: KtorLessonService): LessonService
}