package my.schoolproject.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.schoolproject.core.designsystem.ui.theme.MyApplicationTheme
import my.schoolproject.profile.presentation.component.ProfileAvatar
import my.schoolproject.profile.presentation.component.ProfileEditButton
import my.schoolproject.profile.presentation.component.ProfileInfoItem

@Composable
fun ProfileRoot(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileScreen(
        modifier = modifier,
        state = state,
        onAction = { action ->
            when (action) {
                ProfileAction.OnEditClick -> onEditClick()
            }
        }
    )
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileState,
    onAction: (ProfileAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        ProfileAvatar(photoUrl = state.photoUrl)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = state.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = state.email,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        ProfileEditButton(onEditClick = { onAction(ProfileAction.OnEditClick) })

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileInfoItem(
                    label = "Display Name",
                    value = state.name
                )
                ProfileInfoItem(
                    label = "Email Address",
                    value = state.email
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

    }
}

@PreviewLightDark
@Composable
fun ProfileScreenPreview() {
    MyApplicationTheme {
        Scaffold {
            ProfileScreen(
                modifier = Modifier.padding(it),
                state = ProfileState(
                    name = "John Doe",
                    email = "john.doe@example.com"
                ),
                onAction = {}
            )
        }
    }
}