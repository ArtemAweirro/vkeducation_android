package com.practicum.vkeducation.domain.usecase.impl

import com.practicum.vkeducation.domain.appdetails.AppSummary
import com.practicum.vkeducation.domain.repository.AppListRepository
import com.practicum.vkeducation.domain.usecase.GetAppListUseCase
import javax.inject.Inject

class GetAppListUseCaseImpl @Inject constructor(
    private val repository: AppListRepository
) : GetAppListUseCase {

    override operator fun invoke(): List<AppSummary> {
        return repository.getAppList()
    }
}