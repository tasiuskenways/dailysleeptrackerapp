package my.id.tasius.boarding.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import my.id.tasius.common.model.UiState
import my.id.tasius.navigation.Screen
import my.id.tasius.navigation.navigateAndClearBackStack
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun BoardingScreen(
    navController: NavController,
    boardingVM: BoardingVM = koinViewModel()
) {

    val boardingData by boardingVM.boardingUiState.collectAsStateWithLifecycle(initialValue = UiState.Loading)

    when (boardingData) {
        is UiState.Loading -> BoardingSkeletonView()
        is UiState.Success -> BoardingView()
        else -> {}
    }

    LaunchedEffect(boardingData) {
        when (boardingData) {
            is UiState.Empty, is UiState.Error -> {
                navController.navigateAndClearBackStack(Screen.Login)
            }
            else -> Unit // No effect for other states
        }
    }

}
@Composable
internal fun BoardingSkeletonView(){

}

@Composable
internal fun BoardingView() {

}