package my.id.tasius.dailysleeptracker

import androidx.compose.ui.window.ComposeUIViewController
import my.id.tasius.dailysleeptracker.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }