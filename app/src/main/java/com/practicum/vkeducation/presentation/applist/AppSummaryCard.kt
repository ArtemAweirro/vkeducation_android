package com.practicum.vkeducation.presentation.applist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.practicum.vkeducation.domain.appdetails.AppSummary
import com.practicum.vkeducation.domain.appdetails.Category
import com.practicum.vkeducation.presentation.theme.VkeducationTheme
import java.util.Locale

@Composable
fun AppSummaryCard(
    modifier: Modifier = Modifier,
    appSummary: AppSummary,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = appSummary.iconUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(12.dp)),
        )
        Spacer(Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = appSummary.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                if (appSummary.rating != null) {
                    Spacer(Modifier.width(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(2.dp))
                        Text(
                            text = String.format(Locale.US, "%.1f", appSummary.rating),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Spacer(Modifier.height(2.dp))
            Text(
                text = getCategoryText(appSummary.category),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = appSummary.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private fun getCategoryText(category: Category): String = when (category) {
    Category.APP -> "Приложения"
    Category.GAME -> "Игры"
    Category.PRODUCTIVITY -> "Производительность"
    Category.SOCIAL -> "Социальные сети"
    Category.EDUCATION -> "Образование"
    Category.ENTERTAINMENT -> "Развлечения"
    Category.MUSIC -> "Музыка"
    Category.VIDEO -> "Видео"
    Category.PHOTOGRAPHY -> "Фотография"
    Category.HEALTH -> "Здоровье"
    Category.SPORTS -> "Спорт"
    Category.NEWS -> "Новости"
    Category.BOOKS -> "Книги"
    Category.BUSINESS -> "Бизнес"
    Category.FINANCE -> "Финансы"
    Category.TRAVEL -> "Путешествия"
    Category.MAPS -> "Карты"
    Category.FOOD -> "Еда"
    Category.SHOPPING -> "Покупки"
    Category.UTILITIES -> "Утилиты"
}

@Preview
@Composable
private fun PreviewAppSummaryCard() {
    VkeducationTheme {
        AppSummaryCard(
            appSummary = AppSummary(
                id = "1",
                name = "Telegram",
                description = "Быстрое и безопасное общение",
                iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                category = Category.SOCIAL,
                rating = 4.8f
            ),
            onClick = {}
        )
    }
}
