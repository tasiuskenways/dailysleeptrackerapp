package my.id.tasius.splashscreen.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import my.id.tasius.common.model.UiState
import my.id.tasius.dailysleeptracker.core.designsystem.theme.DailySleepThemeValues
import my.id.tasius.dailysleeptracker.core.designsystem.theme.Drawable
import my.id.tasius.splashscreen.utils.SplashScreenState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashScreen(
    onFinishLoading: (SplashScreenState) -> Unit,
    splashScreenVM: SplashScreenVM = koinViewModel()
) {

    val alpha = remember { Animatable(0f) }

    val splashState by splashScreenVM.splashScreenState.collectAsState(initial = UiState.Loading)

    LaunchedEffect(splashState) {
        when(val state = splashState) {
            is UiState.Success -> {
                onFinishLoading(state.data)
            }
            UiState.Loading -> {
                alpha.animateTo(1f, animationSpec = tween(durationMillis = 1000))
            }
            else -> {
                onFinishLoading(SplashScreenState.LOGGED_OUT)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DailySleepThemeValues.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Drawable.Icon),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(DailySleepThemeValues.spacing.huge * 2)
                .height(DailySleepThemeValues.spacing.huge * 2)
        )
    }

}