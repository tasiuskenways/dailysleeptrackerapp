package my.id.tasius.dailysleeptracker.feature.splashscreen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import my.id.tasius.dailysleeptracker.feature.splashscreen.utils.SplashScreenState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(
    onFinishLoad: (Boolean) -> Unit,
    splashScreenVM: SplashScreenVM = koinViewModel()
) {

    val splashState by splashScreenVM.splashScreenState.collectAsState()

    LaunchedEffect(Unit) {
        when(splashState) {
            SplashScreenState.LOGGED_IN -> onFinishLoad(true)
            SplashScreenState.FIRST_RUN -> onFinishLoad(false)
            SplashScreenState.LOGGED_OUT -> onFinishLoad(false)
        }
    }

}