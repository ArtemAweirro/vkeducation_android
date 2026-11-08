package com.practicum.vkeducation.data.datasource

import com.practicum.vkeducation.data.dto.AppSummaryDto

class AppListLocalDataSource {

    fun getAppList(): List<AppSummaryDto> {
        return listOf(
            AppSummaryDto(
                id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
                name = "Гильдия Героев: Экшен ММО РПГ",
                description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и зразите мастера подземелья!",
                iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                category = "GAME"
            ),
            AppSummaryDto(
                id = "fa2e31b8-1234-4cf7-9914-108a170a1b02",
                name = "VK Мессенджер",
                description = "Общайтесь с друзьями, близкими и коллегами в удобном мессенджере.",
                iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                category = "SOCIAL"
            ),
            AppSummaryDto(
                id = "fa2e31b8-1234-4cf7-9914-108a170a1b03",
                name = "VK Музыка",
                description = "Миллионы треков, плейлисты и подкасты на любой вкус.",
                iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                category = "MUSIC"
            ),
            AppSummaryDto(
                id = "fa2e31b8-1234-4cf7-9914-108a170a1b04",
                name = "VK Видео",
                description = "Шоу, сериалы, фильмы и блогеры в высоком качестве.",
                iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                category = "VIDEO"
            ),
            AppSummaryDto(
                id = "fa2e31b8-1234-4cf7-9914-108a170a1b05",
                name = "Почта Mail.ru",
                description = "Быстрый и надежный почтовый клиент с умным фильтром писем.",
                iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                category = "PRODUCTIVITY"
            )
        )
    }
}
