package com.practicum.vkeducation.domain.appdetails

data class Content(
    val appDetails: AppDetails,
    val similarApps: List<SimilarApp>,
    val showcaseApps: List<AppSummary>,
    val descriptionCollapsed: Boolean,
)
