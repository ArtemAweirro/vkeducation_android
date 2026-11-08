package com.practicum.vkeducation.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.vkeducation.data.repository.AppListRepositoryImpl
import com.practicum.vkeducation.domain.appdetails.AppSummary
import com.practicum.vkeducation.domain.usecase.GetAppListUseCase
import com.practicum.vkeducation.domain.usecase.impl.GetAppListUseCaseImpl
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AppListUiState(
    val appSummaryList: List<AppSummary> = emptyList(),
    val isLoading: Boolean = false,
)

sealed interface AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent
}

class AppListViewModel(
    private val getAppListUseCase: GetAppListUseCase = GetAppListUseCaseImpl(AppListRepositoryImpl())
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppListUiState())
    val uiState: StateFlow<AppListUiState> = _uiState.asStateFlow()

    private val _eventFlow = Channel<AppListEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    init {
        loadAppList()
    }

    private fun loadAppList() {
        val appList = getAppListUseCase()
        _uiState.update { it.copy(appSummaryList = appList) }
    }

    fun onToolbarClick() {
        viewModelScope.launch {
            _eventFlow.send(AppListEvent.ShowSnackbar("Клик по тулбару"))
        }
    }
}
