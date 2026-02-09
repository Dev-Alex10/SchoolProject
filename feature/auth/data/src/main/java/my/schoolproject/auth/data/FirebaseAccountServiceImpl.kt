package my.schoolproject.auth.data

import android.util.Log
import androidx.core.net.toUri
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.tasks.await
import my.schoolproject.auth.domain.FirebaseAccountService
import my.schoolproject.auth.domain.user.User
import my.schoolproject.core.domain.DataError
import my.schoolproject.core.domain.EmptyResult
import my.schoolproject.core.domain.Result
import my.schoolproject.core.domain.asEmptyResult
import javax.inject.Inject

private const val TAG = "FirebaseAccountService"

internal class FirebaseAccountServiceImpl @Inject constructor() : FirebaseAccountService {
    private val auth = Firebase.auth

    override fun hasUser(): Boolean {
        return auth.currentUser != null
    }

    override fun getUser(): Result<User, DataError.Remote> {
        val user = auth.currentUser ?: return Result.Failure(DataError.Remote.NOT_FOUND)
        return Result.Success(user.toDomain())
    }

    override suspend fun login(email: String, password: String): Result<User, DataError.Remote> {
        val task = auth.signInWithEmailAndPassword(email, password)
        return runAuthTaskSafely {
            val firebaseUser = task.await()
            val user = firebaseUser.user
                ?: return@runAuthTaskSafely Result.Failure(DataError.Remote.NOT_FOUND)
            Result.Success(user.toDomain())
        }
    }

    override suspend fun forgotPassword(email: String): EmptyResult<DataError.Remote> {
        val task = auth.sendPasswordResetEmail(email)
        return runAuthTaskSafely {
            task.await()
            Result.Success(Unit).asEmptyResult()
        }
    }

    override suspend fun register(
        email: String,
        password: String
    ): Result<User, DataError.Remote> {
        val task = auth.createUserWithEmailAndPassword(email, password)
        return runAuthTaskSafely {
            val firebaseUser = task.await()
            val user = firebaseUser.user
                ?: return@runAuthTaskSafely Result.Failure(DataError.Remote.NOT_FOUND)
            Result.Success(user.toDomain())
        }
    }

    override suspend fun updateProfile(
        name: String?,
        email: String?,
        photoUrl: String?
    ): EmptyResult<DataError.Remote> {
        val profileUpdates = userProfileChangeRequest {
            displayName = name
            photoUrl?.let {
                photoUri = it.toUri()
            }
        }
        val task = auth.currentUser?.updateProfile(profileUpdates) ?: return Result.Failure(
            DataError.Remote.NOT_FOUND
        ).asEmptyResult()
        return runAuthTaskSafely {
            task.await()
            Result.Success(Unit).asEmptyResult()
        }
    }

    override suspend fun deleteAccount(): EmptyResult<DataError.Remote> {
        val task = auth.currentUser?.delete() ?: return Result.Failure(DataError.Remote.NOT_FOUND)
            .asEmptyResult()
        return runAuthTaskSafely {
            task.await()
            Result.Success(Unit).asEmptyResult()
        }
    }

    override fun logout() {
        auth.signOut()
    }

    private suspend fun <T> runAuthTaskSafely(authTask: suspend () -> Result<T, DataError.Remote>): Result<T, DataError.Remote> {
        return try {
            authTask()
        } catch (exception: FirebaseAuthInvalidUserException) {
            Log.e(TAG, "FirebaseAuthInvalidUserException ${exception.message}")
            Result.Failure(DataError.Remote.NOT_FOUND)
        } catch (exception: FirebaseAuthInvalidCredentialsException) {
            Log.e(TAG, "FirebaseAuthInvalidCredentialsException ${exception.message}")
            Result.Failure(DataError.Remote.UNAUTHORIZED)
        } catch (exception: FirebaseAuthUserCollisionException) {
            Log.e(TAG, "FirebaseAuthUserCollisionException ${exception.message}")
            Result.Failure(DataError.Remote.CONFLICT)
        } catch (exception: Exception) {
            Log.e(TAG, "Unknown Exception ${exception.message}")
            Result.Failure(DataError.Remote.UNKNOWN)
        }
    }
}

private fun FirebaseUser.toDomain(): User {
    return User(
        uid = uid,
        name = displayName ?: "",
        email = email ?: "",
        photoUrl = photoUrl?.toString()
    )
}