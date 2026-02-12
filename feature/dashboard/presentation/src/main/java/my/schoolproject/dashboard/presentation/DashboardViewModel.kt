package my.schoolproject.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import my.schoolproject.core.domain.auth.AuthRepository
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()


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

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}