package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
  darkColorScheme(
    primary = NeosCyan,
    onPrimary = NeosNavyDark,
    primaryContainer = NeosBlueDark,
    onPrimaryContainer = NeosCyan,
    secondary = NeosBlueLight,
    onSecondary = NeosNavyDark,
    tertiary = NeosGreenAccent,
    background = NeosNavyDark,
    onBackground = NeosTextPrimary,
    surface = NeosNavySurface,
    onSurface = NeosTextPrimary,
    surfaceVariant = NeosNavyCard,
    onSurfaceVariant = NeosTextSecondary,
    outline = NeosNavyCardBorder
  )

private val LightColorScheme =
  lightColorScheme(
    primary = NeosBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE0F2FE),
    onPrimaryContainer = Color(0xFF0369A1),
    secondary = NeosCyan,
    onSecondary = NeosNavyDark,
    tertiary = NeosGreenAccent,
    background = Color(0xFFF8FAFC),
    onBackground = Color(0xFF0F172A),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = Color(0xFF475569),
    outline = Color(0xFFCBD5E1)
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = true, // Default to signature fintech navy palette
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}

