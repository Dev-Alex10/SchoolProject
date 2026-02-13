package my.schoolproject.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import my.schoolproject.core.domain.auth.AuthRepository
import my.schoolproject.core.domain.onSuccess
import my.schoolproject.dashboard.domain.LessonRepository
import my.schoolproject.dashboard.presentation.model.toUi
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val lessonRepository: LessonRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state = _state.onStart {
        getAllLessons()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DashboardState()
    )


    fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.OnDetailsClick -> {
                _state.update {
                    it.copy(selectedLessonId = action.id)
                }
            }

            DashboardAction.OnLogoutClick -> logout()
        }
    }

    private fun getAllLessons() {
        viewModelScope.launch {
            lessonRepository.getAllLessons().onSuccess { lessons ->
                _state.update {
                    it.copy(lessonModules = lessons.map { lesson -> lesson.toUi() })
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}