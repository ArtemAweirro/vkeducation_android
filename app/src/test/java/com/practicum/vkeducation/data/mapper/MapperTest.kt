package com.practicum.vkeducation.data.mapper

import com.practicum.vkeducation.data.dto.AppDetailsDto
import com.practicum.vkeducation.data.dto.AppSummaryDto
import com.practicum.vkeducation.domain.appdetails.Category
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun testAppSummaryDtoToDomainValidCategory() {
        val dto = AppSummaryDto(
            id = "1",
            name = "Test App",
            iconUrl = "icon.png",
            description = "Desc",
            category = "APP"
        )
        val domain = dto.toDomain()
        assertEquals("1", domain.id)
        assertEquals("Test App", domain.name)
        assertEquals("icon.png", domain.iconUrl)
        assertEquals("Desc", domain.description)
        assertEquals(Category.APP, domain.category)
    }

    @Test
    fun testAppSummaryDtoToDomainInvalidCategory() {
        val dto = AppSummaryDto(
            id = "2",
            name = "Test App 2",
            iconUrl = "icon2.png",
            description = "Desc 2",
            category = "UNKNOWN_CAT"
        )
        val domain = dto.toDomain()
        assertEquals(Category.APP, domain.category)
    }

    @Test
    fun testAppDetailsDtoToDomainAppCategory() {
        val dto = AppDetailsDto(
            id = "3",
            name = "App Details",
            developer = "Dev",
            category = "Приложения",
            ageRating = 12,
            size = 45.5f,
            iconUrl = "icon.png",
            screenshotUrlList = listOf("scr1.png"),
            description = "Full Desc"
        )
        val domain = dto.toDomain()
        assertEquals("3", domain.id)
        assertEquals(Category.APP, domain.category)
        assertEquals(12, domain.ageRating)
        assertEquals(45.5f, domain.size)
        assertEquals(listOf("scr1.png"), domain.screenshotUrlList)
    }

    @Test
    fun testAppDetailsDtoToDomainGameCategory() {
        val dto = AppDetailsDto(
            id = "4",
            name = "Game App",
            developer = "Dev",
            category = "Игры",
            ageRating = 16,
            size = 120.0f,
            iconUrl = "icon.png",
            screenshotUrlList = emptyList(),
            description = "Game Desc"
        )
        val domain = dto.toDomain()
        assertEquals(Category.GAME, domain.category)
    }

    @Test
    fun testAppDetailsDtoToDomainUnknownCategory() {
        val dto = AppDetailsDto(
            id = "5",
            name = "Unknown App",
            developer = "Dev",
            category = "НеизвестнаяКатегория",
            ageRating = 0,
            size = 10.0f,
            iconUrl = "icon.png",
            screenshotUrlList = null,
            description = "Desc"
        )
        val domain = dto.toDomain()
        assertEquals(Category.APP, domain.category)
    }

    @Test
    fun testAppDetailsDtoToDomainNullScreenshotList() {
        val dto = AppDetailsDto(
            id = "6",
            name = "No Screenshots",
            developer = "Dev",
            category = "Социальные сети",
            ageRating = 18,
            size = 20.0f,
            iconUrl = "icon.png",
            screenshotUrlList = null,
            description = "Desc"
        )
        val domain = dto.toDomain()
        assertEquals(Category.SOCIAL, domain.category)
        assertEquals(null, domain.screenshotUrlList)
    }
}
