package com.practicum.vkeducation.domain.repository

import com.practicum.vkeducation.domain.appdetails.AppDetails
import com.practicum.vkeducation.domain.appdetails.AppSummary

interface AppListRepository {
    suspend fun getAppList(): List<AppSummary>
    suspend fun getAppDetails(id: String): AppDetails
    suspend fun getRating(id: String): Float
    suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float>
    suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float>
}
