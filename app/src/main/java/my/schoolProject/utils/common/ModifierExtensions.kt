package my.schoolProject.utils.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.textFieldModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(16.dp, 4.dp)
}

fun Modifier.buttonModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(16.dp, 8.dp)
}
