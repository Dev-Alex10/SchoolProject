package my.schoolProject.utils.composable

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun BasicToolbar(@StringRes title: Int) {
    TopAppBar(
        title = { Text(stringResource(title)) },
        backgroundColor = MaterialTheme.colors.primary
    )
}
