package com.practicum.vkeducation.presentation.appdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.practicum.vkeducation.domain.appdetails.Content

@Composable
fun AppDetailsScreen(
    content: Content
) {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) { contentPadding ->
        AppDetailsContent(
            content = content,
            onBackClick = {
            },
            onShareClick = {
            },
            onInstallClick = {
            },
            onReadMoreClick = {
            },
            onSimilarAppDownloadClick = {
            },
            onDeveloperClick = {
            },
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(contentPadding),
        )
    }
}