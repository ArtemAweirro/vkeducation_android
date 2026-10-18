package com.practicum.vkeducation.domain.appdetails

data class Content(
    val appDetails: AppDetails,
    val similarApps: List<SimilarApp>,
    val descriptionCollapsed: Boolean,
)
