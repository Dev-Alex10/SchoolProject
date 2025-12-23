package my.schoolproject.auth_data.remote

interface FirebaseAccountService {
    fun hasUser(): Boolean
    fun isAnonymousUser(): Boolean
    fun getUserId(): String
    fun signIn(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun sendRecoveryEmail(email: String, onResult: (Throwable?) -> Unit)
    fun createAnonymousAccount(onResult: (Throwable?) -> Unit)
    fun createAccount(name: String, email: String, password: String, onResult: (Throwable?) -> Unit)
    fun updateProfile(name: String? = null, email: String? = null, photoUrl: String? = null)
    fun deleteAccount(onResult: (Throwable?) -> Unit)
    fun signOut()
}