package my.schoolProject.ui.classroom

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import my.schoolProject.R
import my.schoolProject.data.source.domain.questionAnswer.QuestionAnswerRepository
import javax.inject.Inject

@HiltViewModel
class ClassroomViewModel @Inject constructor(
    private val repository: QuestionAnswerRepository,
    @ApplicationContext private val context: Context
) :
    ViewModel() {
    var uiState = mutableStateOf(ClassroomUiState())
        private set

    init {
        getQuestions()
    }

    fun onAnswerChange(newValue: String, question: String) {
        uiState.value = uiState.value.copy(
            answer = uiState.value.answer + Pair(
                question,
                newValue
            )
        )
    }


    private fun getQuestions() {
        viewModelScope.launch {
            try {
                val questions = repository.getQuestions()
                uiState.value = uiState.value.copy(questions = questions)
            } catch (e: Exception) {
                uiState.value =
                    uiState.value
                        .copy(errorMessage = "${context.getString(R.string.error)} ${e.message}")
            }
        }
    }

    fun checkIfValid(idAnswer: Long, answerGiven: String, question: String) {
        //check questions and answers, and if they are valid
        viewModelScope.launch {
            val correctAnswer = repository.getAnswer(idAnswer)
            uiState.value = uiState.value.copy(
                correctAnswer = uiState.value.correctAnswer + Pair(
                    question,
                    correctAnswer.answerString
                )
            )
            if (correctAnswer.answerString.lowercase() == answerGiven.lowercase()) {
                uiState.value =
                    uiState.value.copy(valid = uiState.value.valid + Pair(question, true))
            } else {
                uiState.value =
                    uiState.value.copy(valid = uiState.value.valid + Pair(question, false))
            }
        }
    }

    fun checkAllValid(): Boolean {
        val valid = !uiState.value.valid.containsValue(false)
        uiState.value = uiState.value.copy(showSolution = true)
        return valid
    }
}
