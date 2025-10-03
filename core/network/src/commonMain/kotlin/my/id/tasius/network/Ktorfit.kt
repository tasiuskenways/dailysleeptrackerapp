package my.id.tasius.network

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient

private const val DEFAULT_BASE_URL = "https://api.sleep.local/"

fun provideKtorfit(
    httpClient: HttpClient,
    baseUrl: String = DEFAULT_BASE_URL,
): Ktorfit =
    Ktorfit.Builder()
        .httpClient(httpClient)
        .baseUrl(baseUrl.ensureTrailingSlash())
        .build()

private fun String.ensureTrailingSlash(): String =
    if (endsWith('/')) this else "$this/"
