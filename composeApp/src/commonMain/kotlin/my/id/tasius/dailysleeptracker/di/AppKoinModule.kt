package my.id.tasius.dailysleeptracker.di

import my.id.tasius.dailysleeptracker.core.network.provideHttpClient
import org.koin.dsl.module

/**
 * Declares the shared dependency graph for the sleep tracker module.
 * Registers the Ktor `HttpClient` so feature code can inject network access.
 */
fun appKoinModule() = module {
    single { provideHttpClient(mock = true) }
}
