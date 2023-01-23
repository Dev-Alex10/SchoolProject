package my.schoolProject.ui.classroom

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import my.schoolProject.utils.composable.QuestionAndAnswer
import my.schoolProject.utils.composable.SignOutButton

@Composable
fun ClassroomView(
    classroomViewModel: ClassroomViewModel = hiltViewModel(),
    onClickSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewState = classroomViewModel.viewState.collectAsState()
    Scaffold(
        content = {
            if (viewState.value.fields.isEmpty()) {
                Text(text = viewState.value.errorMessage, color = Color.Red)
            } else {
                QuestionAndAnswer(modifier, classroomViewModel)
            }
        },
        floatingActionButton = {
            SignOutButton(classroomViewModel, onClickSignOut)
        })
}

