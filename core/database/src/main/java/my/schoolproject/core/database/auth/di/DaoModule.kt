package my.schoolproject.core.database.auth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.schoolproject.core.database.auth.UserDatabase

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun provideUserDao(database: UserDatabase) = database.userDao
}