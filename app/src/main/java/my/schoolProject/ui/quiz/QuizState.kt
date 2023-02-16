package my.schoolProject.ui.quiz

data class QuizState(
    val questions: Set<Question>,
    val userAnswers: Map<Question, String>,
    val showResult: Boolean,
    val allAnswered: Boolean,
    val errorMessage: String
)

data class QuizViewState(
    val fields: List<FieldViewState>,
    val showResult: Boolean,
    val allAnswered: Boolean,
    val errorMessage: String
)

fun QuizState.toViewState(): QuizViewState {
    val multipleChoiceFields = questions.map { question ->
        val userInput = userAnswers[question].orEmpty()
        when (question) {
            is Question.MultipleChoiceQuestion -> {
                question.toViewState(userInput, showResult)
            }
            is Question.TextQuestion -> {
                question.toViewState(userInput, showResult)
            }
        }

    }
    return QuizViewState(multipleChoiceFields, showResult, allAnswered, errorMessage)
}
