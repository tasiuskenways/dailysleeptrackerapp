package my.id.tasius.boarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import my.id.tasius.boarding.ui.BoardingScreen
import my.id.tasius.navigation.Screen

fun NavGraphBuilder.attachBoardingNavigation(navController: NavController) {
    composable<Screen.Boarding> {
        BoardingScreen(navController)
    }
}