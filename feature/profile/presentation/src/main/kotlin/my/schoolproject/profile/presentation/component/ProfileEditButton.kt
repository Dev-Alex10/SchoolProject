package my.schoolproject.profile.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import my.schoolproject.profile.presentation.R

@Composable
fun ProfileEditButton(onEditClick: () -> Unit) {
    Button(
        onClick = onEditClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(R.drawable.feature_profile_presentation_profile_icon),
            contentDescription = null,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text("Edit Profile")
    }
}