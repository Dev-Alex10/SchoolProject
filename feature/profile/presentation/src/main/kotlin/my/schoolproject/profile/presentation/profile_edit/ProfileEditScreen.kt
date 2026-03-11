package my.schoolproject.profile.presentation.profile_edit

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.schoolproject.core.designsystem.ui.theme.MyApplicationTheme
import my.schoolproject.core.presentation.util.LocalSnackbarHostState
import my.schoolproject.core.presentation.util.ObserveAsEvents
import my.schoolproject.profile.presentation.R
import my.schoolproject.profile.presentation.component.ProfileAvatarButton

@Composable
fun ProfileEditRoot(
    modifier: Modifier = Modifier,
    onSaveSuccess: () -> Unit,
    onCancel: () -> Unit,
    viewModel: ProfileEditViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarState = LocalSnackbarHostState.current
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { viewModel.onAction(ProfileEditAction.OnPhotoSelected(it)) }
    }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is ProfileEditEvent.OnError -> snackbarState.showSnackbar(event.error)
            ProfileEditEvent.OnSuccess -> onSaveSuccess()
        }
    }

    ProfileEditScreen(
        modifier = modifier.imePadding(),
        state = state,
        onAction = { action ->
            when (action) {
                ProfileEditAction.OnCancelClick -> onCancel()
                ProfileEditAction.OnPhotoClick -> photoPickerLauncher.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun ProfileEditScreen(
    modifier: Modifier = Modifier,
    state: ProfileEditState,
    onAction: (ProfileEditAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier.size(120.dp),
            contentAlignment = Alignment.Center
        ) {
            ProfileAvatarButton(
                photoUrl = state.photoUrl,
                onClick = {
                    onAction(ProfileEditAction.OnPhotoClick)
                })
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable { onAction(ProfileEditAction.OnPhotoClick) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.feature_profile_presentation_camera),
                    contentDescription = "Change photo",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            if (state.photoUrl != null) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .clickable { onAction(ProfileEditAction.OnRemovePhotoClick) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.feature_profile_presentation_close),
                        contentDescription = "Remove photo",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            state = state.nameTextFieldState,
            label = { Text("Display Name") },
            modifier = Modifier.fillMaxWidth(),
            lineLimits = TextFieldLineLimits.SingleLine,
            isError = state.nameTextFieldState.text.isEmpty(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            state = state.emailTextFieldState,
            lineLimits = TextFieldLineLimits.SingleLine,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email Address") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = !state.isEmailValid && state.emailTextFieldState.text.isNotEmpty(),
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onAction(ProfileEditAction.OnSaveClick) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isSaving && state.canSave
        ) {
            if (state.isSaving) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text("Save Changes")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { onAction(ProfileEditAction.OnCancelClick) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isSaving
        ) {
            Text("Cancel")
        }
    }
}

@PreviewLightDark
@Composable
fun ProfileEditScreenPreview() {
    MyApplicationTheme {
        ProfileEditScreen(
            state = ProfileEditState(),
            onAction = {}
        )
    }
}