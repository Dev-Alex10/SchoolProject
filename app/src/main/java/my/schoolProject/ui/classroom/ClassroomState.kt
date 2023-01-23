package my.schoolProject.ui.classroom

import java.util.*

data class Question(
    val value: String,
    val correctAnswer: String
)

data class State(
    val questions: Set<Question>,
    val userAnswers: Map<Question, String>,
    val showResult: Boolean,
    val errorMessage: String
)

data class ViewState(val fields: List<Field>, val showResult: Boolean, val errorMessage: String) {
    data class Field(val question: Question, val userInput: String, val state: FieldState) {
        sealed class FieldState
        object Neutral : FieldState()
        object Correct : FieldState()
        data class Wrong(val correctAnswer: String) : FieldState()
    }
}

fun State.toViewState(): ViewState {
    val fields = questions.map { question ->
        val userInput = userAnswers[question].orEmpty()
        ViewState.Field(
            question = question,
            userInput = userInput, state = if (showResult) {
                if (userInput.lowercase(Locale.getDefault()) ==
                    question.correctAnswer.lowercase(Locale.getDefault())
                ) {
                    ViewState.Field.Correct
                } else {
                    ViewState.Field.Wrong(question.correctAnswer)
                }
            } else {
                ViewState.Field.Neutral
            }
        )
    }
    return ViewState(fields, showResult, errorMessage)
}
