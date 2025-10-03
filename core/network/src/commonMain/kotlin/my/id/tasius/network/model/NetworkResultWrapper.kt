package my.id.tasius.network.model

sealed class NetworkResultWrapper<out T> {
    data class Success<T>(val data: T, val statusCode: Int) : NetworkResultWrapper<T>()
    data class Error(
        val statusCode: Int,
        val message: String?,
        val cause: Throwable? = null,
    ) : NetworkResultWrapper<Nothing>()
}