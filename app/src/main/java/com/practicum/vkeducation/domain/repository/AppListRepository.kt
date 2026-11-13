package com.practicum.vkeducation.domain.repository

import com.practicum.vkeducation.domain.appdetails.AppDetails
import com.practicum.vkeducation.domain.appdetails.AppSummary

interface AppListRepository {
    suspend fun getAppList(): List<AppSummary>
    suspend fun getAppDetails(id: String): AppDetails
}
