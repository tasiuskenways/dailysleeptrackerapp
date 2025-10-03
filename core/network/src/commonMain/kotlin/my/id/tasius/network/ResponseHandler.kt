package my.id.tasius.network

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlin.coroutines.cancellation.CancellationException

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T, val statusCode: Int) : NetworkResult<T>()
    data class Error(
        val statusCode: Int,
        val message: String?,
        val cause: Throwable? = null,
    ) : NetworkResult<Nothing>()
}

class ResponseHandler {
    suspend inline fun <reified T> handle(response: HttpResponse): NetworkResult<T> {
        return if (response.status.isSuccess()) {
            runCatching { response.body<T>() }
                .map { body -> NetworkResult.Success(body, response.status.value) }
                .getOrElse { throwable ->
                    if (throwable is CancellationException) throw throwable
                    NetworkResult.Error(response.status.value, throwable.message, throwable)
                }
        } else {
            NetworkResult.Error(response.status.value, response.safeBodyAsText())
        }
    }
}

private suspend fun HttpResponse.safeBodyAsText(): String? =
    runCatching { bodyAsText() }
        .onFailure { throwable ->
            if (throwable is CancellationException) throw throwable
        }
        .getOrNull()
