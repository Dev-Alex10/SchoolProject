package my.schoolProject.ui.quiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import my.schoolProject.ui.quiz.FieldViewState.MultipleChoiceViewState.Choice
import my.schoolProject.utils.composable.CorrectOrIncorrectIcon

@Composable
fun Choice(
    choice: Choice,
    enabled: Boolean,
    onSelectedAnswer: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 1.dp)
            .clickable {
                onSelectedAnswer()
            },
        verticalAlignment = CenterVertically
    ) {
        RadioButton(
            selected = choice.selected,
            onClick = { onSelectedAnswer() },
            enabled = enabled
        )
        Text(
            text = choice.text
        )
        Spacer(modifier = Modifier.width(10.dp))
    }
}
