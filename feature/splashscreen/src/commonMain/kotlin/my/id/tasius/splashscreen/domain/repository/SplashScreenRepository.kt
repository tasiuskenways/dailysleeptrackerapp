package my.id.tasius.splashscreen.domain.repository

/**
 * Repository interface for managing splash screen related data.
 *
 * This interface defines the contract for accessing information relevant to the
 * splash screen, such as whether the application is being run for the first time
 * or if the user is currently logged in.
 */
interface SplashScreenRepository {
    /**
     * Retrieves a boolean value indicating whether the application is being run for the first time.
     *
     * This function is a suspend function, meaning it can perform asynchronous operations
     * without blocking the main thread. It's typically used to check if initial setup
     * or onboarding processes need to be performed.
     *
     * @return `true` if it's the first run of the application, `false` otherwise.
     */
    suspend fun getIsFirstRun(): Boolean
    /**
     * Checks if the user is currently logged in.
     *
     * @return True if the user is logged in, false otherwise.
     */
    suspend fun getIsLoggedIn(): Boolean
}