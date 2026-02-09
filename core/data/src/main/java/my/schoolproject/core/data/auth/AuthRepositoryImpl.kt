package my.schoolproject.core.data.auth

import my.schoolproject.core.domain.DataError
import my.schoolproject.core.domain.EmptyResult
import my.schoolproject.core.domain.asEmptyResult
import my.schoolproject.core.domain.auth.AuthRepository
import my.schoolproject.core.domain.auth.FirebaseAccountService
import my.schoolproject.core.domain.auth.user.UserRepository
import my.schoolproject.core.domain.onSuccess
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val firebaseAccountService: FirebaseAccountService,
    private val userRepository: UserRepository
) : AuthRepository {
    override suspend fun login(email: String, password: String): EmptyResult<DataError> {
        val result = firebaseAccountService.login(email, password)
        return result.onSuccess {
            userRepository.upsert(it)
        }.asEmptyResult()
    }

    override suspend fun register(email: String, password: String): EmptyResult<DataError> {
        val result = firebaseAccountService.register(email, password)
        return result.onSuccess {
            userRepository.upsert(it)
        }.asEmptyResult()
    }

    override suspend fun logout() {
        firebaseAccountService.logout()
        userRepository.deleteAllUsers()
    }

}
