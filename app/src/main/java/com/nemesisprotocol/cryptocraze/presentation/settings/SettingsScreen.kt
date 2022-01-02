
package com.nemesisprotocol.cryptocraze.presentation.settings

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.nemesisprotocol.cryptocraze.Screen

@Composable
fun SettingsScreen(navController: NavController) {
    SettingsMenuLink(
        icon = { Icon(imageVector = Icons.Default.Info, contentDescription = "About") },
        title = { Text(text = "About") },
        onClick = {
            navController.navigate(Screen.About.route)
        },
    )
}