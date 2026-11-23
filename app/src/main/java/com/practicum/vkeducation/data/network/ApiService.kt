package com.practicum.vkeducation.data.network

import com.practicum.vkeducation.data.dto.AppDetailsDto
import com.practicum.vkeducation.data.dto.AppSummaryDto
import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.time.Duration.Companion.milliseconds

interface ApiService {
    @GET("catalog")
    suspend fun getCatalog(): List<AppSummaryDto>

    @GET("{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto

    suspend fun getRating(id: String): Float {
        delay(1500.milliseconds)
        return (30..50).random() / 10f
    }
}
