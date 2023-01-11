package my.schoolProject.utils.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun BottomBar(modifier: Modifier, onClickProfile: () -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        item {
            AsyncImage(
                model = "https://images.unsplash.com/photo-1671275285749-1d89bf2504f7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80",
                contentDescription = "Profile Pic",
                modifier = Modifier
                    .clip(RoundedCornerShape(20))
                    .clickable { onClickProfile() }
            )
            Text(text = "${Firebase.auth.currentUser?.displayName}")
        }
    }
}
