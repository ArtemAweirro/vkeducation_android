package com.practicum.vkeducation.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.vkeducation.domain.appdetails.AppSummary
import com.practicum.vkeducation.domain.repository.AppListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

data class AppListUiState(
    val appSummaryList: List<AppSummary> = emptyList(),
    val isLoading: Boolean = false,
    val sequentialTimeMs: Long? = null,
    val parallelTimeMs: Long? = null,
)

sealed interface AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent
}

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val repository: AppListRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppListUiState())
    val uiState: StateFlow<AppListUiState> = _uiState.asStateFlow()

    private val _eventFlow = Channel<AppListEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    init {
        loadAppListAndMeasure()
    }

    private fun loadAppListAndMeasure() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val appList = runCatching { repository.getAppList() }.getOrDefault(emptyList())
            _uiState.update { it.copy(appSummaryList = appList) }

            val ids = appList.map { it.id }
            if (ids.isNotEmpty()) {
                // 1. Последовательная загрузка рейтингов
                var seqRatings: Map<String, Float>
                val seqTime = measureTimeMillis {
                    seqRatings = repository.loadRatingsSequentially(ids)
                }
                _uiState.update { state ->
                    state.copy(
                        appSummaryList = state.appSummaryList.map { app ->
                            app.copy(rating = seqRatings[app.id])
                        },
                        sequentialTimeMs = seqTime
                    )
                }

                // 2. Параллельная загрузка рейтингов
                var parRatings: Map<String, Float>
                val parTime = measureTimeMillis {
                    parRatings = repository.loadRatingsInParallel(ids)
                }
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        appSummaryList = state.appSummaryList.map { app ->
                            app.copy(rating = parRatings[app.id])
                        },
                        parallelTimeMs = parTime
                    )
                }
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun loadRatingsSequentially() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val currentList = _uiState.value.appSummaryList.ifEmpty { repository.getAppList() }
            val ids = currentList.map { it.id }
            var ratings: Map<String, Float>
            val timeMs = measureTimeMillis {
                ratings = repository.loadRatingsSequentially(ids)
            }
            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    appSummaryList = currentList.map { app ->
                        app.copy(rating = ratings[app.id])
                    },
                    sequentialTimeMs = timeMs
                )
            }
        }
    }

    fun loadRatingsInParallel() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val currentList = _uiState.value.appSummaryList.ifEmpty { repository.getAppList() }
            val ids = currentList.map { it.id }
            var ratings: Map<String, Float>
            val timeMs = measureTimeMillis {
                ratings = repository.loadRatingsInParallel(ids)
            }
            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    appSummaryList = currentList.map { app ->
                        app.copy(rating = ratings[app.id])
                    },
                    parallelTimeMs = timeMs
                )
            }
        }
    }

    fun onToolbarClick() {
        viewModelScope.launch {
            _eventFlow.send(AppListEvent.ShowSnackbar("Клик по тулбару"))
        }
    }
}
