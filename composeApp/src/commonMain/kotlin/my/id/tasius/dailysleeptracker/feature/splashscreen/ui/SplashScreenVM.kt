package my.id.tasius.dailysleeptracker.feature.splashscreen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import my.id.tasius.dailysleeptracker.feature.splashscreen.domain.repository.SplashScreenRepository
import my.id.tasius.dailysleeptracker.feature.splashscreen.utils.SplashScreenState

class SplashScreenVM(
    private val splashScreenRepository: SplashScreenRepository
) : ViewModel() {

    val _splashScreenState = MutableStateFlow(SplashScreenState.FIRST_RUN)
    val splashScreenState: StateFlow<SplashScreenState> = _splashScreenState

    init {
        getSplashScreenState()
    }

    private fun getSplashScreenState() = viewModelScope.launch(Dispatchers.IO) {
        val isFirstRun = splashScreenRepository.getIsFirstRun()
        println("TASIUSLOG | isFirstRun: $isFirstRun")
        _splashScreenState.value =
            if (isFirstRun) SplashScreenState.FIRST_RUN else SplashScreenState.LOGGED_OUT

    }


}