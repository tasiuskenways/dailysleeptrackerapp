package my.id.tasius.dailysleeptracker.di

import my.id.tasius.common.storage.DataStoreHelper
import my.id.tasius.common.storage.DataStoreHelperImpl
import my.id.tasius.network.ResponseHandler
import my.id.tasius.network.provideHttpClient
import my.id.tasius.network.provideKtorfit
import my.id.tasius.splashscreen.di.splashModule
import org.koin.dsl.module

/**
 * Declares the shared dependency graph for the sleep tracker module.
 * Registers the Ktor `HttpClient` so feature code can inject network access.
 */
fun appKoinModule() = module {
    single { provideHttpClient(mock = true) }
    single { provideKtorfit(get()) }
    single { ResponseHandler() }
    single<DataStoreHelper> { DataStoreHelperImpl() }
    includes(splashModule())
}
