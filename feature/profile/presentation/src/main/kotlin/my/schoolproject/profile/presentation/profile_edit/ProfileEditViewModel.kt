package my.schoolproject.profile.presentation.profile_edit

import android.util.Log
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import my.schoolproject.core.domain.auth.user.UserRepository
import my.schoolproject.core.presentation.validator.EmailValidator
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val eventChannel = Channel<ProfileEditEvent>()
    val events = eventChannel.receiveAsFlow()

    private var currentUserId: String? = null
    private var initialName = ""
    private var initialEmail = ""
    private var initialPhotoUrl: String? = null

    private val _state = MutableStateFlow(ProfileEditState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadProfile()
                observeValidationStates()
                hasLoadedInitialData = true
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _state.value,
        )

    private val isEmailValidFlow =
        snapshotFlow { state.value.emailTextFieldState.text.trim().toString() }
            .onEach { Log.d(this::class.java.simpleName, "Email changed: $it") }
            .map { email -> EmailValidator.validate(email) }
            .distinctUntilChanged()

    private val isNameValidFlow =
        snapshotFlow { state.value.nameTextFieldState.text.trim().toString() }
            .onEach { Log.d(this::class.java.simpleName, "Name changed: $it") }
            .map { name -> name.isNotEmpty() }
            .distinctUntilChanged()

    private val hasChangesFlow = combine(
        snapshotFlow {
            val currentState = state.value
            currentState.nameTextFieldState.text.trim().toString() != initialName ||
                    currentState.emailTextFieldState.text.trim().toString() != initialEmail
        },
        _state.map { it.photoUrl }.distinctUntilChanged()
    ) { textChanges, photoChanges ->
        textChanges || photoChanges != initialPhotoUrl
    }.distinctUntilChanged()

    private suspend fun loadProfile() {
        try {
            val user = repository.getCurrentUser().first()

            currentUserId = user.uid
            initialName = user.name
            initialEmail = user.email
            initialPhotoUrl = user.photoUrl
            _state.update {
                it.emailTextFieldState.edit { replace(0, length, user.email) }
                it.nameTextFieldState.edit { replace(0, length, user.name) }
                it.copy(
                    photoUrl = user.photoUrl
                )
            }

        } catch (e: Exception) {
            eventChannel.send(
                ProfileEditEvent.OnError(e.message ?: "Failed to load profile")
            )
        }
    }

    private fun observeValidationStates() {
        combine(
            isEmailValidFlow,
            isNameValidFlow,
            hasChangesFlow
        ) { isEmailValid, isNameValid, hasChanges ->
            _state.update {
                it.copy(
                    isEmailValid = isEmailValid,
                    isNameValid = isNameValid,
                    canSave = isEmailValid && isNameValid && hasChanges
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onAction(action: ProfileEditAction) {
        when (action) {
            ProfileEditAction.OnSaveClick -> {
                saveProfile()
            }

            is ProfileEditAction.OnPhotoSelected -> {
                _state.update { it.copy(photoUrl = action.uri.toString()) }
            }

            ProfileEditAction.OnRemovePhotoClick -> {
                _state.update { it.copy(photoUrl = null) }
            }

            else -> {/*Do nothing*/
            }
        }
    }


    private fun saveProfile() {
        val userId = currentUserId
        if (userId == null) {
            viewModelScope.launch {
                eventChannel.send(ProfileEditEvent.OnError("User not loaded"))
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            try {
                repository.updateUser(state.value.toDomain(userId))
                eventChannel.send(ProfileEditEvent.OnSuccess)
            } catch (e: Exception) {
                Log.e(this::class.java.simpleName, "Error saving profile", e)
                eventChannel.send(ProfileEditEvent.OnError("Error saving profile: ${e.message}"))
            } finally {
                _state.update { it.copy(isSaving = false) }
            }
        }
    }
}