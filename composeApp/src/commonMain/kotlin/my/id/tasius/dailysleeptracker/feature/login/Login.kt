package my.id.tasius.dailysleeptracker.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Login() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Green)
    )
}

@Preview
@Composable
fun LoginPreview() {
    Login()
}

