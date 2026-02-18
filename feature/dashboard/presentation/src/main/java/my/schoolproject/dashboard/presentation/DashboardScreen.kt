@file:OptIn(ExperimentalMaterial3Api::class)

package my.schoolproject.dashboard.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.schoolproject.core.designsystem.ui.theme.MyApplicationTheme
import my.schoolproject.dashboard.presentation.R.drawable
import my.schoolproject.dashboard.presentation.component.LessonListItem

@Composable
fun DashboardRoot(
    modifier: Modifier = Modifier,
    onDetailsClick: (Int) -> Unit,
    onLogout: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DashboardScreen(
        modifier = modifier,
        state = state,
        onAction = { action ->
            when (action) {
                is DashboardAction.OnDetailsClick -> onDetailsClick(action.id)
                DashboardAction.OnLogoutClick -> onLogout()
            }
            viewModel.onAction(action)
        })
}

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    state: DashboardState,
    onAction: (DashboardAction) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(state.lessonModules) { lesson ->
            LessonListItem(
                lesson = lesson,
                isSelected = state.selectedLessonId == lesson.id,
                onClick = {
                    onAction(DashboardAction.OnDetailsClick(lesson.id))
                }
            )
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(onClick = {
            onAction(DashboardAction.OnLogoutClick)
        }) {
            Icon(
                painter = painterResource(id = drawable.feature_dashboard_presentation_logout),
                contentDescription = "Logout"
            )
        }
    }
}


@PreviewLightDark
@Composable
fun DashBoardScreenPreview() {
    MyApplicationTheme {
        DashboardScreen(
            state = DashboardState(),
            onAction = {})
    }
}