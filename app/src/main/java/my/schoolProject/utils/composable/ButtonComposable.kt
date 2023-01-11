package my.schoolProject.utils.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import my.schoolProject.ui.classroom.ClassroomViewModel

@Composable
fun BasicTextButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
    TextButton(onClick = action, modifier = modifier) {
        Text(text = stringResource(text))
    }
}

@Composable
fun BasicButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text(text = stringResource(text), fontSize = 16.sp)
    }
}

@Composable
fun SignOutButton(
    classroomViewModel: ClassroomViewModel,
    onClickSignOut: () -> Unit
) {
    Button(
        onClick = { classroomViewModel.signOut(onClickSignOut) },
        shape = RoundedCornerShape(30)
    ) {
        Text(text = "Sign out")
    }
}
