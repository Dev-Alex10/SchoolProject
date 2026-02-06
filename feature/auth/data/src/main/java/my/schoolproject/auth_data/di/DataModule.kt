package my.schoolproject.auth_data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.schoolproject.auth_data.auth.AuthRepositoryImpl
import my.schoolproject.auth_data.auth.FirebaseAccountServiceImpl
import my.schoolproject.auth_data.user.OfflineUserRepository
import my.schoolproject.domain.auth.AuthRepository
import my.schoolproject.domain.auth.FirebaseAccountService
import my.schoolproject.domain.user.UserRepository

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
    @Binds
    internal abstract fun bindsAccountService(firebaseService: FirebaseAccountServiceImpl): FirebaseAccountService

    @Binds
    internal abstract fun bindsUserRepository(userRepository: OfflineUserRepository): UserRepository

    @Binds
    internal abstract fun bindsAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository
}