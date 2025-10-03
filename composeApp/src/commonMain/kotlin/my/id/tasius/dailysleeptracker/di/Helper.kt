package my.id.tasius.dailysleeptracker.di

import io.kotzilla.sdk.analytics.koin.analytics
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Bootstrap Koin with the shared module and an optional platform-specific configuration.
 * Forward `appDeclaration` to extend the graph (e.g., Android context bindings).
 */
fun initKoin(appDeclaration: KoinAppDeclaration? = null) =
    startKoin {
        appDeclaration?.invoke(this)
        modules(appKoinModule())
        analytics()
    }
