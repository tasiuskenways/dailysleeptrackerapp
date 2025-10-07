package my.id.tasius.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import my.id.tasius.dashboard.ui.Dashboard
import my.id.tasius.navigation.Screen

fun NavGraphBuilder.attachDashboardNavigation() {
    composable<Screen.Dashboard> {
        Dashboard()
    }
}