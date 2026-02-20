package my.schoolproject.core.data.auth.user

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import my.schoolproject.core.database.auth.dao.UserDao
import my.schoolproject.core.database.auth.entity.toDatabaseEntity
import my.schoolproject.core.database.auth.entity.toDomain
import my.schoolproject.core.domain.auth.FirebaseAccountService
import my.schoolproject.core.domain.auth.user.User
import my.schoolproject.core.domain.auth.user.UserRepository
import my.schoolproject.core.domain.onFailure
import my.schoolproject.core.domain.onSuccess
import javax.inject.Inject

internal class OfflineFirstUserRepository @Inject constructor(
    private val userDao: UserDao,
    private val firebaseAccountService: FirebaseAccountService
) : UserRepository {

    override fun getCurrentUser(): Flow<User> {
        return userDao.getCurrentUser()
            .map { it?.toDomain() ?: throw Exception("User not found") }
            .catch {
                firebaseAccountService.getUser().onSuccess {
                    updateUser(it)
                    emit(it)
                }
            }
    }

    override suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)?.toDomain()

    override suspend fun updateUser(user: User) {
        firebaseAccountService.updateEmail(user.email).onFailure {
            Log.e(this::class.java.simpleName, "Update email ${it.name}")
            throw Exception("Error updating email")
        }.onSuccess {
            firebaseAccountService.updateProfile(
                name = user.name,
                photoUrl = user.photoUrl
            ).onFailure {
                Log.e(this::class.java.simpleName, "Update profile ${it.name}")
                throw Exception("Error updating profile")
            }
            userDao.upsert(user.toDatabaseEntity())
        }
    }

    override suspend fun deleteAllUsers() = userDao.deleteAll()
}
