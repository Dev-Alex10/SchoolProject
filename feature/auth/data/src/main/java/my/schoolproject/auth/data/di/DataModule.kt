package my.schoolproject.auth.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.schoolproject.auth.data.AuthRepositoryImpl
import my.schoolproject.auth.data.FirebaseAccountServiceImpl
import my.schoolproject.auth.data.user.OfflineUserRepository
import my.schoolproject.auth.domain.AuthRepository
import my.schoolproject.auth.domain.FirebaseAccountService
import my.schoolproject.auth.domain.user.UserRepository

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