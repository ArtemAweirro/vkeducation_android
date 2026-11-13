package com.practicum.vkeducation.data.mapper

import com.practicum.vkeducation.data.dto.AppDetailsDto
import com.practicum.vkeducation.domain.appdetails.AppDetails
import com.practicum.vkeducation.domain.appdetails.Category

fun AppDetailsDto.toDomain(): AppDetails {
    return AppDetails(
        id = id,
        name = name,
        developer = developer,
        category = parseCategory(category),
        ageRating = ageRating,
        size = size,
        iconUrl = iconUrl,
        screenshotUrlList = screenshotUrlList,
        description = description
    )
}

private fun parseCategory(categoryName: String): Category {
    return when (categoryName) {
        "Приложения" -> Category.APP
        "Игры" -> Category.GAME
        "Производительность" -> Category.PRODUCTIVITY
        "Социальные сети" -> Category.SOCIAL
        "Образование" -> Category.EDUCATION
        "Развлечения" -> Category.ENTERTAINMENT
        "Музыка" -> Category.MUSIC
        "Видео" -> Category.VIDEO
        "Фотография" -> Category.PHOTOGRAPHY
        "Здоровье и фитнес" -> Category.HEALTH
        "Спорт" -> Category.SPORTS
        "Новости" -> Category.NEWS
        "Книги" -> Category.BOOKS
        "Бизнес" -> Category.BUSINESS
        "Финансы" -> Category.FINANCE
        "Путешествия" -> Category.TRAVEL
        "Карты" -> Category.MAPS
        "Еда и напитки" -> Category.FOOD
        "Покупки" -> Category.SHOPPING
        "Утилиты" -> Category.UTILITIES
        else -> Category.APP
    }
}