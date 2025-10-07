package my.id.tasius.dailysleeptracker

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import my.id.tasius.dailysleeptracker.core.designsystem.theme.DailySleepTheme
import my.id.tasius.dailysleeptracker.navigation.NavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    DailySleepTheme {
        val navController = rememberNavController()
        NavGraph(navController)
    }
}