package my.schoolProject.ui.classroom

import my.schoolProject.data.source.domain.questionAnswer.model.Question

data class ClassroomUiState(
    val answer: Map<String, String> = emptyMap(),//key question, value answer
    val questions: List<Question> = emptyList(),
    val valid: Map<String,Boolean> = emptyMap(),
    val showSolution: Boolean = false,
    val errorMessage: String = "",
    val correctAnswer: Map<String, String> = emptyMap()
)
