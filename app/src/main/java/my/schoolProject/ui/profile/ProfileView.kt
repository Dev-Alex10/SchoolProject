package my.schoolProject.ui.profile

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Float.min
import kotlin.math.max

@Composable
fun ProfileView(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val currentUser = Firebase.auth.currentUser!!
    CollapsibleScreen()
/*
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = "https://onaircode.com/wp-content/uploads/2018/04/UID_05.png",
            contentDescription = "Profile Background",
            modifier = Modifier
                .matchParentSize()
                .padding(bottom = 40.dp)
                .align(Alignment.TopStart)
                .clickable { },
            contentScale = ContentScale.FillBounds
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
                .clickable { }
        ) {
            AsyncImage(
                model = "https://images.unsplash.com/photo-1671275285749-1d89bf2504f7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80",
                contentDescription = "Profile Pic",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(128.dp)
                    .border(BorderStroke(4.dp, Color.Black), RectangleShape)
                    .clickable {
                    }
            )
            Column(modifier = Modifier) {
                Text(text = "Email: ${currentUser.email!!}")
                Text(text = "Name: ${currentUser.displayName!!}")
                Text(text = "Description: I'm 22 and counting 🤓")
            }
        }
    }*/
}

@Composable
fun CollapsibleScreen() {
    val list = (1..100).map { "Item $it" }
    val scrollState = rememberLazyListState()
    val scrollOffset: Float = min(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset / 600f +
                scrollState.firstVisibleItemIndex)
    )
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = "https://onaircode.com/wp-content/uploads/2018/04/UID_05.png",
            contentDescription = "Profile Background",
            modifier = Modifier
                .matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        Column {
            ProfileCardCollapsingToolbar(scrollOffset)
            Spacer(modifier = Modifier.height(2.dp))
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp)
                    .background(Color.White)
                    .clickable { }
            ) {
                items(list) { item ->
                    Row {
                        Text(text = item)
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileCardCollapsingToolbar(scrollOffset: Float) {
    val imageSize by animateDpAsState(targetValue = max(72.dp, 128.dp * scrollOffset))
    val linesCount = max(3f, scrollOffset * 6).toInt()
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, start = 25.dp)
            .clickable { },
    ) {
        AsyncImage(
            model = "https://images.unsplash.com/photo-1671275285749-1d89bf2504f7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80",
            contentDescription = "Profile Pic",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(imageSize)
                .border(BorderStroke(4.dp, Color.Black), RectangleShape)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = Firebase.auth.currentUser?.displayName!!)
            Text(
                text = Firebase.auth.currentUser?.email!!,
                maxLines = linesCount
            )
        }
    }
}
