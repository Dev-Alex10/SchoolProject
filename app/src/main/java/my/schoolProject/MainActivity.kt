package my.schoolProject


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolApp()
        }
    }
}
/* private lateinit var auth: FirebaseAuth

 override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
     val binding = ActivityMainBinding.inflate(layoutInflater)
     setContentView(binding.root)

     binding.btnSignIn.setOnClickListener {
         if (binding.emailEditText.text != null || binding.passwordEditText.text != null) {
             signIn(
                 binding.emailEditText.text.toString(),
                 binding.passwordEditText.text.toString()
             )
         }
     }
 }

 public override fun onStart() {
     super.onStart()
     // Initialize Firebase Auth
     auth = Firebase.auth
     // Check if user is signed in (non-null) and update UI accordingly.
     val currentUser = auth.currentUser
     if (currentUser != null) {
         reload();
     }
 }

 private fun signUp(email: String, password: String) {
     auth.createUserWithEmailAndPassword(email, password)
         .addOnCompleteListener(this) { task ->
             if (task.isSuccessful) {
                 // Sign in success, update UI with the signed-in user's information
                 Log.d(TAG, "createUserWithEmail:success")
                 val user = auth.currentUser
                 updateUI(user)
             } else {
                 // If sign in fails, display a message to the user.
                 Log.w(TAG, "createUserWithEmail:failure", task.exception)
                 Toast.makeText(
                     baseContext, "Create account failed.",
                     Toast.LENGTH_SHORT
                 ).show()
                 updateUI(null)
             }
         }
 }

 private fun signIn(email: String, password: String) {
     auth.signInWithEmailAndPassword(email, password)
         .addOnCompleteListener(this) { task ->
             if (task.isSuccessful) {
                 // Sign in success, update UI with the signed-in user's information
                 Log.d(TAG, "signInWithEmail:success")
                 val user = auth.currentUser
                 updateUI(user)
             } else {
                 // If sign in fails, display a message to the user.
                 Log.w(TAG, "signInWithEmail:failure", task.exception)
                 Toast.makeText(
                     baseContext, "Authentication failed. ${task.exception}",
                     Toast.LENGTH_SHORT
                 ).show()
                 updateUI(null)
             }
         }
 }

 private fun reload() {

 }

 private fun updateUI(user: FirebaseUser?) {

 }

 companion object {
     private const val TAG = "AuthView"
 }
}
*/
