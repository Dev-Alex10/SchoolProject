package my.schoolproject.core.designsystem.ui.topbar

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import my.schoolproject.core.designsystem.R

@Composable
fun TopBarNavigationIcon(onBackClick: () -> Unit) {
    IconButton(
        onClick = onBackClick,
        content = {
            Icon(
                painter = painterResource(R.drawable.core_designsystem_arrow_back),
                contentDescription = stringResource(R.string.core_designsystem_back)
            )
        }
    )
}