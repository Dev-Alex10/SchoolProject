@file:OptIn(ExperimentalMaterial3Api::class)

package my.schoolproject.dashboard.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import my.schoolproject.core.designsystem.ui.theme.MyApplicationTheme

@Composable
fun DashboardScreen(
    topAppBarTitle: String,
    onLogout: () -> Unit,
    viewModel: DashBoardViewModel = hiltViewModel()
) {
    val list = (1..100).toList()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(topAppBarTitle) }
            )
        }
    ) { padding ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(list) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(onClick = {}) {
                        Text(text = "Module $it")
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(onClick = {
                viewModel.logout()
                onLogout()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.feature_dashboard_presentation_logout),
                    contentDescription = "Logout"
                )
            }
        }
    }
}


@PreviewLightDark
@Composable
fun DashBoardScreenPreview() {
    MyApplicationTheme {
        DashboardScreen(
            "Home",
            onLogout = {}
        )
    }
}