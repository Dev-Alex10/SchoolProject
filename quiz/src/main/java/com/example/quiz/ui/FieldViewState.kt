package com.example.quiz.ui

import com.example.quiz.ui.FieldViewState.MultipleChoiceViewState
import com.example.quiz.ui.FieldViewState.TextFieldViewState
import com.example.quiz.ui.Question.MultipleChoiceQuestion
import com.example.quiz.ui.Question.TextQuestion
import java.util.*

sealed interface Question {
    val value: String
    val correctAnswer: String

    data class MultipleChoiceQuestion(
        override val value: String,
        override val correctAnswer: String,
        val choices: List<String>
    ) : Question

    data class TextQuestion(
        override val value: String,
        override val correctAnswer: String
    ) : Question
}

sealed interface FieldViewState {
    val question: Question
    val resultState: ResultState

    sealed class ResultState
    object Neutral : ResultState()
    object Correct : ResultState()
    data class Wrong(val correctAnswer: String) : ResultState()

    data class TextFieldViewState(
        override val question: TextQuestion,
        override val resultState: ResultState,
        val userInput: String
    ) : FieldViewState

    data class MultipleChoiceViewState(
        override val question: MultipleChoiceQuestion,
        override val resultState: ResultState,
        val choices: List<Choice>
    ) : FieldViewState {
        data class Choice(
            val text: String,
            val selected: Boolean
        )
    }
}

fun TextQuestion.toViewState(
    userInput: String,
    showResult: Boolean
): TextFieldViewState {
    return TextFieldViewState(
        question = this,
        userInput = userInput, resultState = if (showResult) {
            if (userInput.lowercase(Locale.getDefault()) ==
                correctAnswer.lowercase(Locale.getDefault())
            ) {
                FieldViewState.Correct
            } else {
                FieldViewState.Wrong(correctAnswer)
            }
        } else {
            FieldViewState.Neutral
        }
    )
}

fun MultipleChoiceQuestion.toViewState(
    userInput: String,
    showResult: Boolean
): MultipleChoiceViewState {
    return MultipleChoiceViewState(
        question = this,
        resultState = if (showResult) {
            if (userInput.lowercase(Locale.getDefault()) ==
                correctAnswer.lowercase(Locale.getDefault())
            ) {
                FieldViewState.Correct
            } else {
                FieldViewState.Wrong(correctAnswer)
            }
        } else {
            FieldViewState.Neutral
        },
        choices = choices.map {
            MultipleChoiceViewState.Choice(it, userInput == it)
        }
    )
}

