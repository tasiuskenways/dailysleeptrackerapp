package my.id.tasius.splashscreen.navigation

import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import my.id.tasius.navigation.Screen
import my.id.tasius.navigation.navigateAndClearBackStack
import my.id.tasius.splashscreen.ui.SplashScreen
import my.id.tasius.splashscreen.utils.SplashScreenState

fun NavGraphBuilder.attachSplashNavigation(navController: NavController) {
    composable<Screen.SplashScreen>(
        exitTransition = { slideOutHorizontally() }
    ) {
        SplashScreen(onFinishLoading = {
            val screenDestination = when (it) {
                SplashScreenState.LOGGED_IN -> Screen.Dashboard
                SplashScreenState.FIRST_RUN -> Screen.Boarding
                SplashScreenState.LOGGED_OUT -> Screen.Login
            }
            navController.navigateAndClearBackStack(screenDestination)
        })
    }
}