package com.practicum.vkeducation.data.network

import com.practicum.vkeducation.data.dto.AppDetailsDto
import com.practicum.vkeducation.data.dto.AppSummaryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("catalog")
    suspend fun getCatalog(): List<AppSummaryDto>

    @GET("{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}
