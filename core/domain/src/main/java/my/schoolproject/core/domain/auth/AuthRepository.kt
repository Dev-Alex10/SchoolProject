package my.schoolproject.core.domain.auth

import my.schoolproject.core.domain.DataError
import my.schoolproject.core.domain.EmptyResult

interface AuthRepository {
    suspend fun login(email: String, password: String): EmptyResult<DataError>
    suspend fun register(email: String, password: String): EmptyResult<DataError>
    suspend fun logout()
}