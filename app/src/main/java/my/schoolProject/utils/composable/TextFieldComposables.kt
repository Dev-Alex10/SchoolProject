package my.schoolProject.utils.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import my.schoolProject.R
import my.schoolProject.ui.classroom.ClassroomViewModel
import my.schoolProject.ui.classroom.ViewState
import my.schoolProject.utils.common.textFieldModifier
import my.schoolProject.R.drawable as AppIcon
import my.schoolProject.R.string as AppText

@Composable
fun NameField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(AppText.name)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Name"
            )
        }
    )
}

@Composable
fun EmailField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier
//            .onKeyEvent {
//            if (it.nativeKeyEvent.keyCode == KEYCODE_ENTER) {
//
//            }
//            false
//        }
        ,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(AppText.email)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun PasswordField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    PasswordField(value, AppText.password, onNewValue, modifier)
}

@Composable
private fun PasswordField(
    value: String,
    @StringRes placeholder: Int,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }

    val icon = if (isVisible) painterResource(AppIcon.ic_visibility_on)
    else painterResource(AppIcon.ic_visibility_off)

    val visualTransformation = if (isVisible) VisualTransformation.None
    else PasswordVisualTransformation()

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(text = stringResource(placeholder)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
        trailingIcon = {
            IconButton(onClick = { isVisible = !isVisible }) {
                Icon(painter = icon, contentDescription = "Visibility")
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation
    )
}

@Composable
fun AnswerField(
    value: String?,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    valid: Boolean,
    show: Boolean
) {
    OutlinedTextField(
        value = value.orEmpty(),
        onValueChange = { onNewValue(it) },
        placeholder = {
            Text(
                text = stringResource(
                    id = AppText.answer
                )
            )
        },
        modifier = modifier,
        trailingIcon = {
            if (valid && show) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = Color.Green
                )
            } else if (show) {
                Icon(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        },
        enabled = !show
    )
}

@Composable
fun QuestionAndAnswer(
    modifier: Modifier,
    classroomViewModel: ClassroomViewModel
) {
    val viewState = classroomViewModel.viewState.collectAsState()
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(viewState.value.fields) {
            Text(text = it.question.value)
            AnswerField(
                value = it.userInput,
                onNewValue = { value ->
                    classroomViewModel.onAnswerChange(
                        value,
                        it.question
                    )
                },
                modifier = Modifier
                    .textFieldModifier(),
                valid = it.state == ViewState.Field.Correct,
                show = viewState.value.showResult
            )
            if (viewState.value.showResult) {
                Text(
                    text = stringResource(id = R.string.correctAnswer)
                            + it.question.correctAnswer
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
//            classroomViewModel.checkIfValidTest(
//                answerState,
//                it.correctAnswer
//            )
        }
        item {
            if (classroomViewModel.checkAllAnswered()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { classroomViewModel.checkAllValid() }, modifier = Modifier.align(
                            Alignment.TopStart
                        )
                    ) {
                        Text(text = "Check")
                    }
                    Button(
                        onClick = { classroomViewModel.clearResults() }, modifier = Modifier.align(
                            Alignment.TopEnd
                        )
                    ) {
                        Text(text = "Clear results")
                    }
                }
            }
        }
    }
}
