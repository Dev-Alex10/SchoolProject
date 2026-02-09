package my.schoolproject.core.domain.auth

import my.schoolproject.core.domain.DataError
import my.schoolproject.core.domain.EmptyResult
import my.schoolproject.core.domain.Result
import my.schoolproject.core.domain.auth.user.User

interface FirebaseAccountService {
    fun hasUser(): Boolean
    fun getUser(): Result<User, DataError.Remote>
    suspend fun login(email: String, password: String): Result<User, DataError.Remote>
    suspend fun forgotPassword(email: String): EmptyResult<DataError.Remote>
    suspend fun register(email: String, password: String): Result<User, DataError.Remote>

    suspend fun updateProfile(
        name: String? = null,
        email: String? = null,
        photoUrl: String? = null
    ): EmptyResult<DataError.Remote>

    suspend fun deleteAccount(): EmptyResult<DataError.Remote>
    fun logout()
}