package com.practicum.vkeducation.domain.usecase

import com.practicum.vkeducation.domain.appdetails.AppSummary

interface GetAppListUseCase {
    operator fun invoke(): List<AppSummary>
}
