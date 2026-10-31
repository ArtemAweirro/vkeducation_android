package com.practicum.vkeducation.data.mapper

import com.practicum.vkeducation.data.dto.AppSummaryDto
import com.practicum.vkeducation.domain.appdetails.AppSummary
import com.practicum.vkeducation.domain.appdetails.Category

fun AppSummaryDto.toDomain(): AppSummary {
    return AppSummary(
        id = id,
        name = name,
        iconUrl = iconUrl,
        description = description,
        category = parseCategory(category)
    )
}

private fun parseCategory(categoryName: String): Category {
    return runCatching { Category.valueOf(categoryName) }
        .getOrDefault(Category.APP)
}
