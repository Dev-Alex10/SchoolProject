package my.schoolproject.profile.presentation.profile_edit

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    // Inject repositories here
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileEditState())
    val state = _state.asStateFlow()

    init {
        loadProfile()
    }

    fun onAction(action: ProfileEditAction) {
        when (action) {
            is ProfileEditAction.OnNameChange -> {
                _state.value = _state.value.copy(
                    name = action.name,
                    nameError = null
                )
            }

            is ProfileEditAction.OnEmailChange -> {
                _state.value = _state.value.copy(
                    email = action.email,
                    emailError = null
                )
            }

            ProfileEditAction.OnPhotoClick -> {
                // TODO: Handle photo selection
            }

            ProfileEditAction.OnSaveClick -> {
                saveProfile()
            }

            ProfileEditAction.OnCancelClick -> {
                // TODO: Handle cancel
            }
        }
    }

    private fun loadProfile() {
        // TODO: Load current user profile
        _state.value = ProfileEditState(
            name = "John Doe",
            email = "john.doe@example.com",
            photoUrl = null
        )
    }

    private fun saveProfile() {
        // TODO: Validate and save profile
        val currentState = _state.value

        // Basic validation
        if (currentState.name.isBlank()) {
            _state.value = currentState.copy(nameError = "Name cannot be empty")
            return
        }

        if (currentState.email.isBlank()) {
            _state.value = currentState.copy(emailError = "Email cannot be empty")
            return
        }

        // TODO: Save to repository
        _state.value = currentState.copy(isSaving = true)

        // Simulate save
        // After save, navigate back
    }
}