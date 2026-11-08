package com.practicum.vkeducation.domain.repository

import com.practicum.vkeducation.domain.appdetails.AppSummary

interface AppListRepository {
    fun getAppList(): List<AppSummary>
}
