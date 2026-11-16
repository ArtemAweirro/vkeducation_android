package com.practicum.vkeducation.presentation.appdetails

import com.practicum.vkeducation.domain.appdetails.AppDetails
import com.practicum.vkeducation.domain.appdetails.AppSummary
import com.practicum.vkeducation.domain.appdetails.SimilarApp

sealed interface InstallState {
    data object NotInstalled : InstallState
    data class Installing(val progress: Int) : InstallState // 0..100
    data object Installed : InstallState
}

sealed interface AppDetailsState {
    data object Loading : AppDetailsState
    data class Content(
        val appDetails: AppDetails,
        val similarApps: List<SimilarApp>,
        val showcaseApps: List<AppSummary>,
        val descriptionCollapsed: Boolean = false,
        val installState: InstallState = InstallState.NotInstalled,
    ) : AppDetailsState
    data object Error : AppDetailsState
}
