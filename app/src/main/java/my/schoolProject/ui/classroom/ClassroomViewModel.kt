package my.schoolProject.ui.classroom

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import my.schoolProject.R
import my.schoolProject.data.source.domain.questionAnswer.QuestionAnswerRepository
import my.schoolProject.data.source.remote.accountService.AccountService
import my.schoolProject.di.DataStoreManager
import javax.inject.Inject

@HiltViewModel
class ClassroomViewModel @Inject constructor(
    private val repository: QuestionAnswerRepository,
    private val accountService: AccountService,
    private val dataStore: DataStoreManager,
    @ApplicationContext private val context: Context
) :
    ViewModel() {
    private val state: MutableStateFlow<State> =
        MutableStateFlow(State(emptySet(), emptyMap(), false, ""))
    val viewState: StateFlow<ViewState> = state
        .map {
            it.toViewState()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = state.value.toViewState()
        )

    init {
        getQuestions()
    }

    fun onAnswerChange(newValue: String, question: Question) {
        state.value =
            state.value.copy(userAnswers = state.value.userAnswers + Pair(question, newValue))
    }

    fun signOut(onClickSignOut: () -> Unit) {
        viewModelScope.launch {
            accountService.signOut()
            dataStore.clear()
            onClickSignOut()
        }
    }

    private fun getQuestions() {
        viewModelScope.launch {
            try {
                val questions = repository.getQuestions()
                questions.forEach {
                    val answer = repository.getAnswer(it.answer_id)
                    state.value = state.value.copy(
                        questions = state.value.questions + Question(
                            it.questionString,
                            correctAnswer = answer.answerString
                        )
                    )
                }
            } catch (e: Exception) {
                state.value =
                    state.value
                        .copy(errorMessage = "${context.getString(R.string.error)} ${e.message}")
            }
        }
    }

    fun checkAllAnswered(): Boolean {
        return state.value.userAnswers.values.size == state.value.questions.size
    }

    fun checkAllValid() {
        state.value = state.value.copy(showResult = true)
    }

    fun clearResults() {
        state.value =
            State(
                questions = state.value.questions,
                userAnswers = emptyMap(),
                showResult = false,
                ""
            )
    }
}
