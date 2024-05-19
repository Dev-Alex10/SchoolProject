package com.example.quiz.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import my.schoolProject.R
import my.schoolProject.utils.composable.CorrectOrIncorrectIcon
import my.schoolProject.utils.composable.SignOutButton

@Composable
fun QuizView(
    quizViewModel: QuizViewModel = hiltViewModel(), onClickSignOut: () -> Unit
) {
    val viewState = quizViewModel.viewState.collectAsState()
    Scaffold(content = { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(viewState.value.fields) { field ->
                when (field) {
                    is FieldViewState.MultipleChoiceViewState -> {
                        Text(text = field.question.value)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            LazyColumn(
                                modifier = Modifier.fillParentMaxHeight(0.35f),
                                userScrollEnabled = false
                            ) {
                                items(field.choices) {
                                    Choice(choice = it,
                                        enabled = !viewState.value.showResult,
                                        onSelectedAnswer = {
                                            quizViewModel.onSelectedAnswer(
                                                newValue = it.text, question = field.question
                                            )
                                        })
                                }
                            }
                            Spacer(modifier = Modifier.width(75.dp))
                            CorrectOrIncorrectIcon(field.resultState)
                        }
                        ShowSolution(
                            viewState.value.showResult && field.resultState is FieldViewState.Wrong,
                            field.question.correctAnswer
                        )
                    }

                    is FieldViewState.TextFieldViewState -> {
                        TextAnswer(
                            question = field.question.value,
                            userInput = field.userInput,
                            state = field.resultState,
                            onNewValue = { value ->
                                quizViewModel.onSelectedAnswer(
                                    value, field.question
                                )
                            },
                            showResult = viewState.value.showResult
                        )
                        ShowSolution(
                            viewState.value.showResult && viewState.value.showResult && field.resultState is FieldViewState.Wrong,
                            field.question.correctAnswer
                        )
                    }
                }

            }
            item {
                Button(
                    onClick = { quizViewModel.checkAllValid() },
                    enabled = viewState.value.allAnswered
                ) {
                    Text(text = "Check Answers")
                }
            }
        }
    }, floatingActionButton = {
        SignOutButton { quizViewModel.signOut(onClickSignOut) }
    })
}

@Composable
private fun ShowSolution(
    showResult: Boolean, correctAnswer: String
) {
    if (showResult) {
        Text(
            text = stringResource(id = R.string.correctAnswer) + correctAnswer,
            modifier = Modifier.background(color = Color.Cyan)
        )
    }
}
