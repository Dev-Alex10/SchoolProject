package my.schoolproject.auth_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import my.schoolproject.auth_domain.repository.UserRepository
import my.schoolproject.auth_domain.use_case.AddUser
import my.schoolproject.auth_domain.use_case.GetUser
import my.schoolproject.auth_domain.use_case.UserUseCases

@Module
@InstallIn(ViewModelComponent::class)
object UserDomainModule {
    @ViewModelScoped
    @Provides
    fun provideUserUseCases(repository: UserRepository) =
        UserUseCases(
            getUser = GetUser(repository),
            addUser = AddUser(repository)
        )
}