package com.practicum.vkeducation.data.repository

import com.practicum.vkeducation.data.datasource.AppListLocalDataSource
import com.practicum.vkeducation.data.mapper.toDomain
import com.practicum.vkeducation.data.network.ApiService
import com.practicum.vkeducation.domain.appdetails.AppDetails
import com.practicum.vkeducation.domain.appdetails.AppSummary
import com.practicum.vkeducation.domain.repository.AppListRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class AppListRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localDataSource: AppListLocalDataSource
) : AppListRepository {

    override suspend fun getAppList(): List<AppSummary> {
        val remoteList = runCatching {
            apiService.getCatalog().map { it.toDomain() }
        }.getOrNull()

        return if (!remoteList.isNullOrEmpty() && remoteList.size >= 10) {
            remoteList
        } else {
            localDataSource.getAppList().map { it.toDomain() }
        }
    }

    override suspend fun getAppDetails(id: String): AppDetails {
        return apiService.getAppDetails(id).toDomain()
    }

    override suspend fun getRating(id: String): Float {
        return apiService.getRating(id)
    }

    override suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> {
        val result = mutableMapOf<String, Float>()
        for (id in ids) {
            result[id] = apiService.getRating(id)
        }
        return result
    }

    override suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> =
        coroutineScope {
            ids.map { id ->
                async { id to apiService.getRating(id) }
        }.awaitAll().toMap()
    }
}
