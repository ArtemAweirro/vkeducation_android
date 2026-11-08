package com.practicum.vkeducation.data.repository

import com.practicum.vkeducation.data.datasource.AppListLocalDataSource
import com.practicum.vkeducation.data.mapper.toDomain
import com.practicum.vkeducation.domain.appdetails.AppSummary
import com.practicum.vkeducation.domain.repository.AppListRepository

class AppListRepositoryImpl(
    private val localDataSource: AppListLocalDataSource = AppListLocalDataSource()
) : AppListRepository {

    override fun getAppList(): List<AppSummary> {
        return localDataSource.getAppList().map { it.toDomain() }
    }
}
