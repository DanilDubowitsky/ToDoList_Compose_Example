package com.example.todolist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = textPrimaryDarkTheme,
    primaryVariant = Purple700,
    secondary = textSecondaryDarkTheme,
    background = screenBackgroundDark
)

private val LightColorPalette = lightColors(
    primary = textPrimaryLightTheme,
    primaryVariant = Purple700,
    secondary = textSecondaryLightTheme,
    background = screenBackgroundLight

    /* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/
)

@Composable
fun ToDoListTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = interTypography,
        shapes = Shapes,
        content = content
    )
}