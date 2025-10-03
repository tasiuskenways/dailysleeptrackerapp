package my.id.tasius.splashscreen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import my.id.tasius.common.model.UiState
import my.id.tasius.splashscreen.domain.usecase.GetSplashStateUseCase
import my.id.tasius.splashscreen.utils.SplashScreenState

class SplashScreenVM(
    private val splashStateUseCase: GetSplashStateUseCase
) : ViewModel() {

    val splashScreenState: Flow<UiState<SplashScreenState>> = flow {
        emit(UiState.Success(splashStateUseCase()))
    }.stateIn(
        scope = viewModelScope,
        initialValue = UiState.Loading,
        started = SharingStarted.WhileSubscribed()
    )

}