package my.id.tasius.dailysleeptracker.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

@Composable
fun DailySleepTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: Typography = DailySleepTypography,
    spacing: DailySleepSpacing = DailySleepSpacing(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val rememberedSpacing = remember(spacing) { spacing }

    CompositionLocalProvider(LocalSpacing provides rememberedSpacing) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = DailySleepShapes,
            content = content
        )
    }
}

object DailySleepThemeDefaults {
    val lightColorScheme: ColorScheme get() = LightColorScheme
    val darkColorScheme: ColorScheme get() = DarkColorScheme
}

object DailySleepThemeTokens {
    val primaryBlue: Color get() = DailySleepColors.PrimaryBlue
    val accentPurple: Color get() = DailySleepColors.AccentPurple
    val lightMutedText: Color get() = DailySleepColors.LightTextSecondary
    val darkMutedText: Color get() = DailySleepColors.DarkTextSecondary
}

object DailySleepThemeValues {
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes

    val spacing: DailySleepSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current
}
