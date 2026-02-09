package my.schoolproject.core.data.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.schoolproject.core.domain.auth.AuthRepository
import my.schoolproject.core.domain.auth.FirebaseAccountService
import my.schoolproject.core.data.auth.AuthRepositoryImpl
import my.schoolproject.core.data.auth.FirebaseAccountServiceImpl
import my.schoolproject.core.data.auth.user.OfflineUserRepository
import my.schoolproject.core.domain.auth.user.UserRepository

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