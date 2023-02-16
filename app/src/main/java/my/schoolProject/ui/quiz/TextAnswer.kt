package my.schoolProject.ui.quiz

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import my.schoolProject.utils.common.textFieldModifier
import my.schoolProject.utils.composable.AnswerField

@Composable
fun TextAnswer(
    question: String,
    userInput: String,
    state: FieldViewState.ResultState,
    onNewValue: (String) -> Unit,
    showResult: Boolean
) {
    Text(text = question)
    AnswerField(
        value = userInput,
        onNewValue = { onNewValue(it) },
        modifier = Modifier
            .textFieldModifier(),
        state = state,
        show = showResult
    )
    Spacer(modifier = Modifier.height(25.dp))
}

