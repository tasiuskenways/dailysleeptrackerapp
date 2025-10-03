package my.id.tasius.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    object SplashScreen : Screen

    @Serializable
    object Login : Screen

    @Serializable
    object Dashboard : Screen

    @Serializable
    object Boarding : Screen

}