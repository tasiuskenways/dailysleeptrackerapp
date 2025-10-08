package my.id.tasius.splashscreen.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import my.id.tasius.splashscreen.domain.repository.SplashScreenRepository
import my.id.tasius.splashscreen.utils.SplashScreenState

class GetSplashStateUseCase(
    private val splashScreenRepository: SplashScreenRepository
) {
    suspend operator fun invoke(): SplashScreenState = withContext(Dispatchers.IO) {
        val isFirstRun = async { splashScreenRepository.getIsFirstRun() }
        val isLoggedIn = async { splashScreenRepository.getIsLoggedIn() }

        return@withContext when {
            isFirstRun.await() -> SplashScreenState.FIRST_RUN
            isLoggedIn.await() -> SplashScreenState.LOGGED_IN
            else -> SplashScreenState.LOGGED_OUT
        }
    }

}