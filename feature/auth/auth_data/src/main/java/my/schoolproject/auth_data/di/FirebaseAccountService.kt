package my.schoolproject.auth_data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import my.schoolproject.auth_data.remote.FirebaseAccountServiceImpl

@Module
@InstallIn
abstract class FirebaseAccountService {
    @Binds
    abstract fun provideAccountService(impl: FirebaseAccountServiceImpl): FirebaseAccountService
}