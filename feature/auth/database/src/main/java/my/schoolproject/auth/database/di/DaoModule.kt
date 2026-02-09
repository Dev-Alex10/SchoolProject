package my.schoolproject.auth.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.schoolproject.auth.database.UserDatabase

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun provideUserDao(database: UserDatabase) = database.userDao
}