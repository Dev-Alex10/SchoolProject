package my.schoolProject.ui.classroom

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    val uiState by classroomViewModel.uiState
    Scaffold(
        content = {
            if (uiState.questions.isEmpty()) {
                Text(text = uiState.errorMessage, color = Color.Red)
            } else {
                QuestionAndAnswer(modifier, uiState, classroomViewModel)
            }
        },
        floatingActionButton = {
            SignOutButton(classroomViewModel, onClickSignOut)
        })
}

