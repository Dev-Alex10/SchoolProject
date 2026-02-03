@file:OptIn(ExperimentalMaterial3Api::class)

package my.schoolproject.auth.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AuthTopAppBar(
    modifier: Modifier = Modifier,
) {
//    CenterAlignedTopAppBar(
//        modifier = modifier,
//        title = {
//            Text(
//                text = stringResource(Resources.String.app_name) + "!",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold
//            )
//        },
//        navigationIcon = {
//            if (authBackStack.contains(AuthRoute.Auth.Register)) {
//                IconButton(
//                    onClick = {
//                        authBackStack.remove(AuthRoute.Auth.Register)
//                    },
//                    content = {
//                        Icon(
//                            painter = painterResource(Resources.Drawable.arrow_back_24),
//                            contentDescription = stringResource(Resources.String.back)
//                        )
//                    }
//                )
//            }
//        },
//    )
}