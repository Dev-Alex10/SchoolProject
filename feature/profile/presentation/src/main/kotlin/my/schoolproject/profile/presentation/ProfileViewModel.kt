package my.schoolproject.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import my.schoolproject.core.domain.auth.user.UserRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    repository: UserRepository
) : ViewModel() {
    val state = repository.getCurrentUser()
        .map { user ->
            ProfileState(
                name = user.name,
                email = user.email,
                photoUrl = user.photoUrl,
                isLoading = false
            )
        }.stateIn(
            scope = viewModelScope,
            initialValue = ProfileState(isLoading = true),
            started = SharingStarted.WhileSubscribed(5000)
        )
}