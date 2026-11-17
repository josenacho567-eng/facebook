package com.example.facebook.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = FacebookBlue,
    secondary = FacebookTextSecondary,
    background = FacebookBackground,
    surface = FacebookSurface,
    onPrimary = FacebookSurface,
    onBackground = FacebookTextPrimary,
    onSurface = FacebookTextPrimary
)

@Composable
fun FacebookTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
