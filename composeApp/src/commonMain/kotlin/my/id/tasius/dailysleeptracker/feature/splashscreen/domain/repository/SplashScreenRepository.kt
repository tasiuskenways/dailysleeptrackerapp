package my.id.tasius.dailysleeptracker.feature.splashscreen.domain.repository

interface SplashScreenRepository {
    suspend fun getIsFirstRun(): Boolean
    suspend fun getIsLoggedIn(): Boolean
}