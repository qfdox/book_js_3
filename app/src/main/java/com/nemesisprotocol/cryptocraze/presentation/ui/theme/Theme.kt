
package com.nemesisprotocol.cryptocraze.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ColorPalette = darkColors(
    primary = Teal500,
    surface = DarkBlue900,
    onSurface = Color.White,
    background = DarkBlue900,
    onBackground = Color.White,
    secondaryVariant = Gray500
)

@Composable
fun CryptoCrazeTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = ColorPalette, typography = Typography, content = content)
}