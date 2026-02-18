@file:OptIn(ExperimentalMaterial3Api::class)

package my.schoolproject.auth.presentation.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import my.schoolproject.auth.presentation.R
import my.schoolproject.core.designsystem.ui.topbar.TopBarNavigationIcon

@Composable
fun AuthTopAppBar(
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(R.string.feature_auth_presentation_top_bar_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            if (onBackClick != null) {
                TopBarNavigationIcon(onBackClick = onBackClick)
            }
        },
    )
}