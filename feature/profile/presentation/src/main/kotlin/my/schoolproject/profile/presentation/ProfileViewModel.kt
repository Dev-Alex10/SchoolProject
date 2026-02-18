package my.schoolproject.profile.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    // Inject repositories here
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        loadProfile()
    }

    fun onAction(action: ProfileAction) {
        when (action) {
            ProfileAction.OnEditClick -> {
                // TODO: Handle edit click
            }
        }
    }

    private fun loadProfile() {
        // TODO: Load user profile data
        _state.value = ProfileState(
            name = "John Doe",
            email = "john.doe@example.com",
            photoUrl = null // Add URL when available
        )
    }
}