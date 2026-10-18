package com.practicum.vkeducation.domain.appdetails

data class AppSummary(
    val id: String,
    val name: String,
    val iconUrl: String,
    val description: String,
    val category: Category,
)
