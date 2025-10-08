package my.id.tasius.boarding.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import my.id.tasius.boarding.ui.model.BoardingUIModel
import my.id.tasius.common.model.UiState

class BoardingVM: ViewModel() {

    val boardingUiState: Flow<UiState<List<BoardingUIModel>>> = flow {
        emit(UiState.Loading)
    }.stateIn(
        scope = viewModelScope,
        initialValue = UiState.Loading,
        started = SharingStarted.WhileSubscribed()
    )

}