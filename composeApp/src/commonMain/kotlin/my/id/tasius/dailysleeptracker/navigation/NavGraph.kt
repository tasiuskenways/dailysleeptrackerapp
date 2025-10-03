package my.id.tasius.dailysleeptracker.navigation

import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import my.id.tasius.dailysleeptracker.feature.boarding.Boarding
import my.id.tasius.dailysleeptracker.feature.login.Login
import my.id.tasius.dashboard.ui.Dashboard
import my.id.tasius.navigation.Screen
import my.id.tasius.splashscreen.ui.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen) {
        composable<Screen.SplashScreen>(
            exitTransition = { slideOutHorizontally() }
        ) {
            SplashScreen(navController)
        }
        composable<Screen.Login> {
            Login()
        }
        composable<Screen.Dashboard> {
            Dashboard()
        }
        composable<Screen.Boarding> {
            Boarding()
        }
    }
}