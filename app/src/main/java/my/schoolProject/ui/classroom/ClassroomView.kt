package my.schoolProject.ui.classroom

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import my.schoolProject.R
import my.schoolProject.utils.common.textFieldModifier
import my.schoolProject.utils.composable.AnswerField

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
        bottomBar = {
            BottomNavigation(
                content = {
                    Text(text = "Profile ${Firebase.auth.currentUser?.displayName}")
                    Button(onClick = { classroomViewModel.signOut(onClickSignOut) }) {
                        Text(text = "Sign out")
                    }
                }
            )
        })
}

@Composable
private fun QuestionAndAnswer(
    modifier: Modifier,
    uiState: ClassroomUiState,
    classroomViewModel: ClassroomViewModel
) {
    LazyColumn(modifier = modifier) {
        items(uiState.questions) {
            Text(text = it.questionString)
            AnswerField(
                value = uiState.answer[it.questionString].orEmpty(),
                onNewValue = { value ->
                    classroomViewModel.onAnswerChange(
                        value,
                        it.questionString
                    )
                },
                modifier = Modifier
                    .textFieldModifier(),
                valid = uiState.valid.isEmpty() || uiState.valid[it.questionString]!!,
                show = uiState.showSolution
            )
            if (uiState.showSolution) {
                Text(
                    text = stringResource(id = R.string.correctAnswer)
                            + uiState.correctAnswer[it.questionString]!!
                )
            }
            classroomViewModel.checkIfValid(
                it.answer_id,
                uiState.answer[it.questionString].orEmpty(),
                it.questionString
            )
        }
        item {
            if (!uiState.answer.containsValue("") && uiState.answer.size == uiState.questions.size) {
                Button(onClick = { classroomViewModel.checkAllValid() }) {
                    Text(text = "Check")
                }
            }
        }
    }
}

