package my.id.tasius.dailysleeptracker.core.common.ui.model

sealed class UiState<out T: Any> {
    object Loading : UiState<Nothing>()
    data class Success<T: Any>(val data: T) : UiState<T>()
    data class Error<T : Any>(val message: String, val data: T? = null) : UiState<T>()
    object Empty : UiState<Nothing>()
}
