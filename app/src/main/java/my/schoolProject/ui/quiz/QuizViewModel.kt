package my.schoolProject.ui.quiz

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
import kotlin.random.Random

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuestionAnswerRepository,
    private val accountService: AccountService,
    private val dataStore: DataStoreManager,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val choices = listOf("Sim", "Nho", "Talvez", "NA")
    private val quizState: MutableStateFlow<QuizState> =
        MutableStateFlow(
            QuizState(
                questions = emptySet(),
                userAnswers = emptyMap(),
                showResult = false,
                allAnswered = false,
                errorMessage = ""
            )
        )
    val viewState: StateFlow<QuizViewState> = quizState.map {
        it.toViewState()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = quizState.value.toViewState()
    )

    init {
        getQuestions()
    }

    private fun getQuestions() {
        viewModelScope.launch {
            try {
                val questions = repository.getQuestions().map {
                    val answer = repository.getAnswer(it.answer_id)
                    val multipleChoice = Random.nextBoolean()
                    if (multipleChoice) {
                        Question.MultipleChoiceQuestion(
                            value = it.questionString,
                            correctAnswer = answer.answerString,
                            choices = choices
                        )
                    } else {
                        Question.TextQuestion(
                            value = it.questionString,
                            correctAnswer = answer.answerString
                        )
                    }
                }
                quizState.update {
                    it.copy(questions = it.questions + questions)
                }
            } catch (e: Exception) {
                quizState.value =
                    quizState.value
                        .copy(errorMessage = "${context.getString(R.string.error)} ${e.message}")
            }
        }
    }

    fun onSelectedAnswer(newValue: String, question: Question) {
        if (quizState.value.showResult) {
            return
        }
        quizState.value = quizState.value.copy(
            userAnswers = quizState.value.userAnswers + Pair(
                question,
                newValue
            )
        )
        checkAllAnswered()
    }

    fun checkAllValid() {
        quizState.value = quizState.value.copy(showResult = true)
    }

    private fun checkAllAnswered() {
        quizState.value =
            quizState.value.copy(allAnswered = quizState.value.userAnswers.values.size == quizState.value.questions.size)
    }

    fun signOut(onClickSignOut: () -> Unit) {
        viewModelScope.launch {
            accountService.signOut()
            dataStore.clear()
            onClickSignOut()
        }
    }

    fun clearResults() {
        quizState.value =
            QuizState(
                questions = quizState.value.questions,
                userAnswers = emptyMap(),
                showResult = false,
                allAnswered = false,
                errorMessage = ""
            )
    }
}
