package my.schoolproject.profile.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import my.schoolproject.profile.presentation.R

@Composable
fun ProfileAvatar(photoUrl: String?) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        ProfileAvatarContent(photoUrl)
    }
}


@Composable
fun ProfileAvatarButton(photoUrl: String?, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        ProfileAvatarContent(photoUrl)
    }
}

@Composable
private fun ProfileAvatarContent(photoUrl: String?) {
    if (photoUrl != null) {
        AsyncImage(model = photoUrl, contentDescription = "Profile photo")
    } else {
        Icon(
            painter = painterResource(R.drawable.feature_profile_presentation_profile_icon),
            contentDescription = "Default profile photo",
            modifier = Modifier.size(60.dp),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}