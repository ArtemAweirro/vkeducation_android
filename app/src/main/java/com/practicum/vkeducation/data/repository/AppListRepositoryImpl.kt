package com.practicum.vkeducation.data.repository

import com.practicum.vkeducation.data.mapper.toDomain
import com.practicum.vkeducation.data.network.ApiService
import com.practicum.vkeducation.domain.appdetails.AppDetails
import com.practicum.vkeducation.domain.appdetails.AppSummary
import com.practicum.vkeducation.domain.repository.AppListRepository
import javax.inject.Inject

class AppListRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AppListRepository {

    override suspend fun getAppList(): List<AppSummary> {
        return apiService.getCatalog().map { it.toDomain() }
    }

    override suspend fun getAppDetails(id: String): AppDetails {
        return apiService.getAppDetails(id).toDomain()
    }
}

