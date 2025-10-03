package my.id.tasius.splashscreen.data.repository

import my.id.tasius.dailysleeptracker.core.common.storage.DataStoreHelper
import my.id.tasius.splashscreen.domain.repository.SplashScreenRepository
import my.id.tasius.splashscreen.utils.SplashConst

/**
 * Implementation of [SplashScreenRepository] that interacts with local data storage.
 *
 * This class is responsible for retrieving information related to the splash screen,
 * such as whether it's the first time the app is being run or if the user is logged in.
 * It uses a [DataStoreHelper] to persist and retrieve this data.
 *
 * @property dataStoreHelper An instance of [DataStoreHelper] used for accessing local data.
 */
class SplashScreenRepositoryImpl(
    private val dataStoreHelper: DataStoreHelper
): SplashScreenRepository {

    /**
     * Retrieves a boolean indicating whether it's the first time the app is being run.
     *
     * This function checks the DataStore for a specific key ([SplashConst.IS_FIRST_RUN_LOCAL_KEY]).
     * If the key is not found (meaning it's the first run), it defaults to `true`.
     * If the value is `false` (meaning it's not the first run), it explicitly sets the value in DataStore
     * to `false` to ensure consistency. This might be redundant if the initial set operation always happens
     * after the first run, but it acts as a safeguard.
     *
     * @return `true` if it's the first run, `false` otherwise.
     */
    override suspend fun getIsFirstRun(): Boolean {
        val isFirstRun = dataStoreHelper.getBoolean(SplashConst.IS_FIRST_RUN_LOCAL_KEY) ?: true
        if (!isFirstRun) {
            dataStoreHelper.setBoolean(SplashConst.IS_FIRST_RUN_LOCAL_KEY, false)
        }
        return isFirstRun
    }

    /**
     * Checks if the user is currently logged in.
     *
     * This function is intended to retrieve the login status from a data source.
     * The specific implementation for checking the login status is pending.
     *
     * @return `true` if the user is logged in, `false` otherwise.
     */
    override suspend fun getIsLoggedIn(): Boolean {
        return false
    }
}