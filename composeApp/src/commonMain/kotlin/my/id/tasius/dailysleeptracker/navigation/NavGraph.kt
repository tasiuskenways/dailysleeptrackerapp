package my.id.tasius.dailysleeptracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import my.id.tasius.boarding.navigation.attachBoardingNavigation
import my.id.tasius.dailysleeptracker.feature.login.Login
import my.id.tasius.dashboard.navigation.attachDashboardNavigation
import my.id.tasius.navigation.Screen
import my.id.tasius.splashscreen.navigation.attachSplashNavigation

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen) {
        attachSplashNavigation(navController)
        attachDashboardNavigation()
        attachBoardingNavigation(navController)
        composable<Screen.Login> {
            Login()
        }
    }
}