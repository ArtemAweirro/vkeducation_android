package com.practicum.vkeducation.presentation.applist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.practicum.vkeducation.domain.appdetails.AppSummary

@Composable
fun AppListScreen(
    appSummaryList: List<AppSummary>,
    onClickApp: () -> Unit
) {
    val currentAppIcon = appSummaryList.firstOrNull()?.iconUrl ?: ""
    Scaffold(
        topBar = {
            Toolbar(appIconUrl = currentAppIcon)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(appSummaryList) { appSummary ->
                AppSummaryCard(
                    appSummary = appSummary,
                    onClick = onClickApp
                )
            }
        }
    }
}
