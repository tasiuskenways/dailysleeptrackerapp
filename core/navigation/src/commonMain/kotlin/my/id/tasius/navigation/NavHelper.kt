package my.id.tasius.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

/**
 * Navigate and clear the entire back stack.
 * Use this when switching to a new flow (e.g., after login/logout).
 *
 * Similar to replacing the entire navigation stack in Android,
 * or redirecting with no history in web routing.
 */
fun NavController.navigateAndClearBackStack(destination: Screen) {
    navigate(destination) {
        // Pop everything including the current destination
        popUpTo(graph.findStartDestination().id) {
            inclusive = true
        }
        // Avoid multiple copies in back stack
        launchSingleTop = true
    }
}

/**
 * Navigate and replace the current screen.
 * Use this for screen-to-screen replacement without growing the stack.
 */
fun NavController.navigateAndReplace(destination: Screen) {
    navigate(destination) {
        // Pop the current screen before navigating
        popUpTo(currentDestination?.route ?: return@navigate) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

/**
 * Standard navigation with single top behavior.
 * Prevents duplicate destinations in back stack.
 */
fun NavController.navigateSingleTop(destination: Screen) {
    navigate(destination) {
        launchSingleTop = true
    }
}

/**
 * Navigate back to a specific destination in the stack.
 * Returns false if destination not found in back stack.
 *
 * Think of this like popping middleware layers until you
 * reach a specific handler in your stack.
 */
fun NavController.navigateBackTo(
    destination: Screen,
    inclusive: Boolean = false
): Boolean {
    return try {
        popBackStack(destination, inclusive)
    } catch (e: Exception) {
        false
    }
}

/**
 * Safe back navigation with fallback.
 * Useful when back stack might be empty (e.g., deep links).
 */
fun NavController.navigateBackOrTo(fallbackDestination: Screen) {
    if (!popBackStack()) {
        navigateAndClearBackStack(fallbackDestination)
    }
}

/**
 * Check if we can navigate back.
 * Helpful for handling back button in UI.
 */
fun NavController.canGoBack(): Boolean {
    return previousBackStackEntry != null
}