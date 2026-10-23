package com.practicum.vkeducation.presentation.appdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.practicum.vkeducation.R
import com.practicum.vkeducation.domain.appdetails.AppSummary
import com.practicum.vkeducation.presentation.applist.AppSummaryCard

@Composable
internal fun Showcase(
    apps: List<AppSummary>,
    onAppClick: (AppSummary) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 3 })

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.showcase),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        HorizontalPager(state = pagerState) { page ->
            val startIndex = page * 3
            val pageApps = apps.subList(startIndex, minOf(startIndex + 3, apps.size))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                pageApps.forEach { app ->
                    AppSummaryCard(
                        appSummary = app,
                        onClick = { onAppClick(app) }
                    )
                }
            }
        }
    }
}
