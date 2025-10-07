package my.id.tasius.dailysleeptracker.core.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object DailySleepColors {
    val PrimaryBlue = Color(0xFF4A90E2)
    val AccentPurple = Color(0xFF7B61FF)

    val LightBackground = Color(0xFFF9FAFB)
    val LightSurface = Color(0xFFFFFFFF)
    val LightTextPrimary = Color(0xFF111827)
    val LightTextSecondary = Color(0xFF6B7280)

    val DarkBackground = Color(0xFF0B0F2A)
    val DarkSurface = Color(0xFF111827)
    val DarkTextPrimary = Color(0xFFE5E7EB)
    val DarkTextSecondary = Color(0xFF9CA3AF)
}

val LightColorScheme = lightColorScheme(
    primary = DailySleepColors.PrimaryBlue,
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD6E6FA),
    onPrimaryContainer = DailySleepColors.LightTextPrimary,
    secondary = DailySleepColors.AccentPurple,
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE8E3FF),
    onSecondaryContainer = DailySleepColors.LightTextPrimary,
    tertiary = Color(0xFF5F8BFF),
    onTertiary = Color(0xFFFFFFFF),
    background = DailySleepColors.LightBackground,
    onBackground = DailySleepColors.LightTextPrimary,
    surface = DailySleepColors.LightSurface,
    onSurface = DailySleepColors.LightTextPrimary,
    surfaceVariant = Color(0xFFE5E7EB),
    onSurfaceVariant = DailySleepColors.LightTextSecondary,
    outline = Color(0xFFCBD2D9),
    inverseSurface = DailySleepColors.LightTextPrimary,
    inverseOnSurface = DailySleepColors.LightSurface,
    inversePrimary = DailySleepColors.AccentPurple,
    scrim = Color(0x66000000)
)

val DarkColorScheme = darkColorScheme(
    primary = DailySleepColors.PrimaryBlue,
    onPrimary = Color(0xFF001A33),
    primaryContainer = Color(0xFF1E3A5F),
    onPrimaryContainer = Color(0xFFCCE2FF),
    secondary = DailySleepColors.AccentPurple,
    onSecondary = Color(0xFF1C0F4C),
    secondaryContainer = Color(0xFF3A2D78),
    onSecondaryContainer = Color(0xFFDAD1FF),
    tertiary = Color(0xFF8DAAFF),
    onTertiary = Color(0xFF041836),
    background = DailySleepColors.DarkBackground,
    onBackground = DailySleepColors.DarkTextPrimary,
    surface = DailySleepColors.DarkSurface,
    onSurface = DailySleepColors.DarkTextPrimary,
    surfaceVariant = Color(0xFF1F2937),
    onSurfaceVariant = DailySleepColors.DarkTextSecondary,
    outline = Color(0xFF374151),
    inverseSurface = DailySleepColors.DarkTextPrimary,
    inverseOnSurface = DailySleepColors.DarkSurface,
    inversePrimary = DailySleepColors.AccentPurple,
    scrim = Color(0x99000000)
)
