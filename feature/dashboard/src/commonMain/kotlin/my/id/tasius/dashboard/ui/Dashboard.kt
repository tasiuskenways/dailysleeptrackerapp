package my.id.tasius.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import my.id.tasius.dailysleeptracker.core.designsystem.theme.DailySleepThemeValues

@Composable
fun Dashboard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DailySleepThemeValues.colorScheme.background)
            .padding(DailySleepThemeValues.spacing.large)
    ) {
        Text(
            text = "Dashboard",
            style = DailySleepThemeValues.typography.headlineMedium,
            color = DailySleepThemeValues.colorScheme.onBackground
        )
    }
}
