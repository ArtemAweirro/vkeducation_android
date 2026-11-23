package com.practicum.vkeducation.data.repository

import com.practicum.vkeducation.data.datasource.AppListLocalDataSource
import com.practicum.vkeducation.data.dto.AppDetailsDto
import com.practicum.vkeducation.data.dto.AppSummaryDto
import com.practicum.vkeducation.data.network.ApiService
import com.practicum.vkeducation.domain.appdetails.Category
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class AppListRepositoryImplTest {

    private val apiService: ApiService = mockk()
    private val localDataSource: AppListLocalDataSource = mockk()
    private val repository = AppListRepositoryImpl(apiService, localDataSource)

    @Test
    fun testGetAppListRemoteSuccessWhenCatalogSizeAtLeast10() = runTest {
        val remoteDtos = (1..10).map {
            AppSummaryDto(
                id = it.toString(),
                name = "App $it",
                iconUrl = "icon$it.png",
                description = "Desc $it",
                category = "APP"
            )
        }
        coEvery { apiService.getCatalog() } returns remoteDtos

        val result = repository.getAppList()

        assertEquals(10, result.size)
        assertEquals("App 1", result[0].name)
        assertEquals(Category.APP, result[0].category)
        coVerify(exactly = 0) { localDataSource.getAppList() }
    }

    @Test
    fun testGetAppListFallbackToLocalWhenCatalogSizeLessThan10() = runTest {
        val remoteDtos = (1..5).map {
            AppSummaryDto(
                id = it.toString(),
                name = "Remote $it",
                iconUrl = "icon.png",
                description = "Desc",
                category = "APP"
            )
        }
        val localDtos = (1..10).map {
            AppSummaryDto(
                id = it.toString(),
                name = "Local $it",
                iconUrl = "icon.png",
                description = "Desc",
                category = "GAME"
            )
        }
        coEvery { apiService.getCatalog() } returns remoteDtos
        coEvery { localDataSource.getAppList() } returns localDtos

        val result = repository.getAppList()

        assertEquals(10, result.size)
        assertEquals("Local 1", result[0].name)
        assertEquals(Category.GAME, result[0].category)
        coVerify(exactly = 1) { localDataSource.getAppList() }
    }

    @Test
    fun testGetAppListFallbackToLocalWhenCatalogThrowsException() = runTest {
        val localDtos = (1..10).map {
            AppSummaryDto(
                id = it.toString(),
                name = "Local $it",
                iconUrl = "icon.png",
                description = "Desc",
                category = "APP"
            )
        }
        coEvery { apiService.getCatalog() } throws RuntimeException("Network error")
        coEvery { localDataSource.getAppList() } returns localDtos

        val result = repository.getAppList()

        assertEquals(10, result.size)
        assertEquals("Local 1", result[0].name)
        coVerify(exactly = 1) { localDataSource.getAppList() }
    }

    @Test
    fun testGetAppDetailsCallsApiServiceAndMapsToDomain() = runTest {
        val dto = AppDetailsDto(
            id = "42",
            name = "Test Details",
            developer = "VK",
            category = "Игры",
            ageRating = 12,
            size = 50.0f,
            iconUrl = "icon.png",
            screenshotUrlList = listOf("s1.png"),
            description = "Detailed desc"
        )
        coEvery { apiService.getAppDetails("42") } returns dto

        val result = repository.getAppDetails("42")

        assertEquals("42", result.id)
        assertEquals("Test Details", result.name)
        assertEquals("VK", result.developer)
        assertEquals(Category.GAME, result.category)
        assertEquals(12, result.ageRating)
        assertEquals(50.0f, result.size)
        assertEquals(listOf("s1.png"), result.screenshotUrlList)
        assertEquals("Detailed desc", result.description)
    }

    @Test
    fun testGetRatingCallsApiService() = runTest {
        coEvery { apiService.getRating("100") } returns 4.7f

        val rating = repository.getRating("100")

        assertEquals(4.7f, rating)
    }

    @Test
    fun testLoadRatingsSequentially() = runTest {
        coEvery { apiService.getRating("1") } returns 4.1f
        coEvery { apiService.getRating("2") } returns 4.5f

        val ratings = repository.loadRatingsSequentially(listOf("1", "2"))

        assertEquals(2, ratings.size)
        assertEquals(4.1f, ratings["1"])
        assertEquals(4.5f, ratings["2"])
    }

    @Test
    fun testLoadRatingsInParallel() = runTest {
        coEvery { apiService.getRating("1") } returns 4.2f
        coEvery { apiService.getRating("2") } returns 4.8f

        val ratings = repository.loadRatingsInParallel(listOf("1", "2"))

        assertEquals(2, ratings.size)
        assertEquals(4.2f, ratings["1"])
        assertEquals(4.8f, ratings["2"])
    }
}
