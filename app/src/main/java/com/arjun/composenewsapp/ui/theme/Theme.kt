package com.arjun.composenewsapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = LightGrey,
    primaryVariant = DarkGrey,
    secondary = Red
)

private val LightColorPalette = lightColors(
    primary = DarkBlue,
    primaryVariant = Red,
    secondary = SuperDarkBlue
)

@Composable
fun ComposeNewsAPPTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}