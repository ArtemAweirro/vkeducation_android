package com.practicum.vkeducation.domain.usecase

import com.practicum.vkeducation.domain.appdetails.AppSummary
import com.practicum.vkeducation.domain.appdetails.Category
import com.practicum.vkeducation.domain.repository.AppListRepository
import com.practicum.vkeducation.domain.usecase.impl.GetAppListUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAppListUseCaseTest {

    private val repository: AppListRepository = mockk()
    private val useCase: GetAppListUseCase = GetAppListUseCaseImpl(repository)

    @Test
    fun testInvokeReturnsAppSummariesSuccessfully() = runTest {
        val expectedList = listOf(
            AppSummary("1", "App 1", "icon1.png", "Desc 1", Category.APP, 4.5f),
            AppSummary("2", "App 2", "icon2.png", "Desc 2", Category.GAME, 4.0f)
        )
        coEvery { repository.getAppList() } returns expectedList

        val result = useCase()

        assertEquals(expectedList, result)
        assertEquals(2, result.size)
        assertEquals("App 1", result[0].name)
    }

    @Test
    fun testInvokeReturnsEmptyListWhenRepositoryEmpty() = runTest {
        coEvery { repository.getAppList() } returns emptyList()

        val result = useCase()

        assertEquals(0, result.size)
        assertEquals(true, result.isEmpty())
    }

    @Test(expected = RuntimeException::class)
    fun testInvokePropagatesExceptionFromRepository() = runTest {
        coEvery { repository.getAppList() } throws RuntimeException("Network error")

        useCase()
    }

    @Test
    fun testInvokeRepositoryCalledExactlyOnce() = runTest {
        coEvery { repository.getAppList() } returns emptyList()

        useCase()

        coVerify(exactly = 1) { repository.getAppList() }
    }

    @Test
    fun testInvokeMultipleCallsTriggerRepositoryMultipleTimes() = runTest {
        val expectedList = listOf(
            AppSummary("1", "App 1", "icon1.png", "Desc 1", Category.APP, 4.5f)
        )
        coEvery { repository.getAppList() } returns expectedList

        useCase()
        useCase()
        useCase()

        coVerify(exactly = 3) { repository.getAppList() }
    }

    @Test
    fun testInvokeResultDataIntegrity() = runTest {
        val appSummary = AppSummary("123", "VK", "vk.png", "Social network", Category.SOCIAL, 4.8f)
        coEvery { repository.getAppList() } returns listOf(appSummary)

        val result = useCase()

        assertEquals(1, result.size)
        val item = result[0]
        assertEquals("123", item.id)
        assertEquals("VK", item.name)
        assertEquals("vk.png", item.iconUrl)
        assertEquals("Social network", item.description)
        assertEquals(Category.SOCIAL, item.category)
        assertEquals(4.8f, item.rating)
    }
}
