package my.id.tasius.dailysleeptracker.feature.splashscreen.data.repository

import my.id.tasius.dailysleeptracker.core.common.storage.DataStoreHelper
import my.id.tasius.dailysleeptracker.feature.splashscreen.domain.repository.SplashScreenRepository
import my.id.tasius.dailysleeptracker.feature.splashscreen.utils.SplashConst

class SplashScreenRepositoryImpl(
    private val dataStoreHelper: DataStoreHelper
): SplashScreenRepository {

    override suspend fun getIsFirstRun(): Boolean {
        val isFirstRun = dataStoreHelper.getBoolean(SplashConst.IS_FIRST_RUN_LOCAL_KEY) ?: true
        if (!isFirstRun) {
            dataStoreHelper.setBoolean(SplashConst.IS_FIRST_RUN_LOCAL_KEY, false)
        }
        return isFirstRun
    }

    override suspend fun getIsLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }
}