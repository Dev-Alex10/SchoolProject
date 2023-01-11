package my.schoolProject.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileView(
    modifier: Modifier = Modifier
) {
    val currentUser = Firebase.auth.currentUser!!
    Column{
        Text(text = currentUser.email!!)
        Text(text = currentUser.displayName!!)
    }
}
