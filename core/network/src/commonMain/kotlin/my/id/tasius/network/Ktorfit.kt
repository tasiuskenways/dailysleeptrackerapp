package my.id.tasius.network

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient

fun provideKtorfit(
    httpClient: HttpClient,
    baseUrl: String = NetworkConstant.BASE_URL,
): Ktorfit =
    Ktorfit.Builder()
        .httpClient(httpClient)
        .baseUrl(baseUrl.ensureTrailingSlash())
        .build()

private fun String.ensureTrailingSlash(): String =
    if (endsWith('/')) this else "$this/"
