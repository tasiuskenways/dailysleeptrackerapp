package my.id.tasius.network.utils

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import my.id.tasius.network.model.NetworkResultWrapper

suspend inline fun <reified T> handleResponse(response: HttpResponse): NetworkResultWrapper<T> {
    return if (response.status.isSuccess()) {
        runCatching { response.body<T>() }
            .map { body -> NetworkResultWrapper.Success(body, response.status.value) }
            .getOrElse { throwable ->
                NetworkResultWrapper.Error(response.status.value, throwable.message, throwable)
            }
    } else {
        NetworkResultWrapper.Error(response.status.value, response.safeBodyAsText())
    }
}

suspend fun HttpResponse.safeBodyAsText(): String? = runCatching { bodyAsText() }.getOrNull()
