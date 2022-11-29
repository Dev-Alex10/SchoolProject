package my.schoolProject.ui.classroom

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import my.schoolProject.utils.common.textFieldModifier
import my.schoolProject.utils.composable.AnswerField

@Composable
fun ClassroomView(
    classroomViewModel: ClassroomViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by classroomViewModel.uiState

    Column(modifier = modifier) {
        Text(text = "What is your name?")
        AnswerField(
            value = uiState.answer,
            onNewValue = classroomViewModel::onAnswerChange,
            modifier = Modifier
                .textFieldModifier(),
            enabled = !uiState.valid
        )
        Button(onClick = { classroomViewModel.checkIfValid(1, 1) }) {
            Text(text = "Check")
        }
    }
}
