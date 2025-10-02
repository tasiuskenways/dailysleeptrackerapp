package my.id.tasius.dailysleeptracker.feature.splashscreen.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import my.id.tasius.dailysleeptracker.core.common.ui.model.UiState
import my.id.tasius.dailysleeptracker.feature.splashscreen.utils.SplashScreenState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(
    onFinishLoad: (SplashScreenState) -> Unit,
    splashScreenVM: SplashScreenVM = koinViewModel()
) {

    val alpha = remember { Animatable(0f) }

    val splashState by splashScreenVM.splashScreenState.collectAsState(initial = UiState.Loading)

    LaunchedEffect(splashState) {
        alpha.animateTo(1f, animationSpec = tween(durationMillis = 1000))
        delay(5000)
        when(val state = splashState) {
            is UiState.Success -> {
                onFinishLoad(state.data)
            }
            UiState.Loading -> {}
            else -> {}
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = "https://cdn.discordapp.com/attachments/1008936658521051177/1406851137553961081/b359bd9dea4bb4ba.jpeg?ex=68dd50f9&is=68dbff79&hm=7d3ff1534232ddc0e7ddbe3cf08bc8fd8141737e26fbab5da114840f366b5d13&",
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.width(64.dp).height(64.dp)
        )
    }

}