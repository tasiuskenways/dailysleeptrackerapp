package my.id.tasius.dailysleeptracker.navigation

import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import my.id.tasius.dailysleeptracker.feature.dashboard.Dashboard
import my.id.tasius.dailysleeptracker.feature.login.Login
import my.id.tasius.dailysleeptracker.feature.splashscreen.ui.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen) {
        composable<Screen.SplashScreen>(
            exitTransition = { slideOutHorizontally() }
        ) { SplashScreen(onFinishLoad = { isLoggedIn ->
            val destination = if (isLoggedIn) Screen.Dashboard else Screen.Login
            navController.popBackStack()
            navController.navigate(destination)
        }) }
        composable<Screen.Login> {
            Login()
        }
        composable<Screen.Dashboard> {
            Dashboard()
        }
    }
}