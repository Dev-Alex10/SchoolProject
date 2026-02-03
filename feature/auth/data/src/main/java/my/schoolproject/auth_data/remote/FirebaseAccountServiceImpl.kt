package my.schoolproject.auth_data.remote

import android.util.Log
import androidx.core.net.toUri
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import javax.inject.Inject

class FirebaseAccountServiceImpl @Inject constructor() : FirebaseAccountService {
    val auth = Firebase.auth

    override fun hasUser(): Boolean {
        return auth.currentUser != null
    }

    override fun isAnonymousUser(): Boolean {
        return auth.currentUser?.isAnonymous ?: true
    }

    override fun getUserId(): String {
        return auth.currentUser?.uid.orEmpty()
    }


    override fun signIn(email: String, password: String, onResult: (Throwable?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            onResult(it.exception)
        }
    }

    override fun sendRecoveryEmail(email: String, onResult: (Throwable?) -> Unit) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener { onResult(it.exception) }
    }

    override fun createAnonymousAccount(onResult: (Throwable?) -> Unit) {
        auth.signInAnonymously().addOnCompleteListener { onResult(it.exception) }
    }

    override fun createAccount(
        name: String,
        email: String,
        password: String,
        onResult: (Throwable?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                updateProfile(name)
            onResult(it.exception)
        }
    }

    override fun updateProfile(name: String?, email: String?, photoUrl: String?) {
        val profileUpdates = userProfileChangeRequest {
            displayName = name
            photoUrl?.let {
                photoUri = it.toUri()
            }
        }
        auth.currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d(this::class.simpleName, "User Profile updated!")
            } else {
                Log.d(this::class.simpleName, "Something went wrong!")
            }
        }
    }

    override fun deleteAccount(onResult: (Throwable?) -> Unit) {
        auth.currentUser!!.delete().addOnCompleteListener { onResult(it.exception) }
    }

    override fun signOut() {
        auth.signOut()
    }
}