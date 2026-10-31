package com.practicum.vkeducation.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.vkeducation.domain.appdetails.AppSummary
import com.practicum.vkeducation.domain.appdetails.Category
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

data class AppListUiState(
    val appSummaryList: List<AppSummary> = emptyList(),
    val isLoading: Boolean = false,
)

sealed interface AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent
}

class AppListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppListUiState())
    val uiState: StateFlow<AppListUiState> = _uiState.asStateFlow()

    private val _eventFlow = Channel<AppListEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    init {
        loadAppList()
    }

    private fun loadAppList() {
        _uiState.value = AppListUiState(
            appSummaryList = listOf(
                AppSummary(
                    id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
                    name = "Гильдия Героев: Экшен ММО РПГ",
                    description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и зразите мастера подземелья!",
                    iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                    category = Category.GAME
                ),
                AppSummary(
                    id = "fa2e31b8-1234-4cf7-9914-108a170a1b02",
                    name = "VK Мессенджер",
                    description = "Общайтесь с друзьями, близкими и коллегами в удобном мессенджере.",
                    iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                    category = Category.SOCIAL
                ),
                AppSummary(
                    id = "fa2e31b8-1234-4cf7-9914-108a170a1b03",
                    name = "VK Музыка",
                    description = "Миллионы треков, плейлисты и подкасты на любой вкус.",
                    iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                    category = Category.MUSIC
                ),
                AppSummary(
                    id = "fa2e31b8-1234-4cf7-9914-108a170a1b04",
                    name = "VK Видео",
                    description = "Шоу, сериалы, фильмы и блогеры в высоком качестве.",
                    iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                    category = Category.VIDEO
                ),
                AppSummary(
                    id = "fa2e31b8-1234-4cf7-9914-108a170a1b05",
                    name = "Почта Mail.ru",
                    description = "Быстрый и надежный почтовый клиент с умным фильтром писем.",
                    iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                    category = Category.PRODUCTIVITY
                )
            )
        )
    }

    fun onToolbarClick() {
        viewModelScope.launch {
            _eventFlow.send(AppListEvent.ShowSnackbar("Клик по тулбару"))
        }
    }
}
