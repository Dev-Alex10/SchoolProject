package my.schoolProject.ui.classroom

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import my.schoolProject.data.source.domain.questionAnswer.QuestionAnswerRepository
import javax.inject.Inject

@HiltViewModel
class ClassroomViewModel @Inject constructor(private val repository: QuestionAnswerRepository) :
    ViewModel() {
    var uiState = mutableStateOf(ClassroomUiState(valid = false))
        private set


    private val answer get() = uiState.value.answer

    fun onAnswerChange(newValue: String) {
        uiState.value = uiState.value.copy(answer = newValue)
    }

    fun checkIfValid(idQuestion: Long, idAnswer: Long) {
        //check questions and answers, and if they are valid
        viewModelScope.launch {
            val question = repository.getQuestion(idQuestion)
            println(question)
            if (question.answer_id == idAnswer) {
                uiState.value = uiState.value.copy(valid = true)
            }
        }
    }
}
