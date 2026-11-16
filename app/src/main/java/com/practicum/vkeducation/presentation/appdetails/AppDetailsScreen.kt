package com.practicum.vkeducation.presentation.appdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicum.vkeducation.domain.appdetails.AppSummary

@Composable
fun AppDetailsScreen(
    onBackClick: () -> Unit,
    onShowcaseAppClick: (AppSummary) -> Unit,
    viewModel: AppDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) { contentPadding ->
        when (val current = state) {
            is AppDetailsState.Content -> {
                AppDetailsContent(
                    content = current,
                    onBackClick = onBackClick,
                    onShareClick = {},
                    onInstallClick = viewModel::onInstallClick,
                    onReadMoreClick = {},
                    onSimilarAppDownloadClick = {},
                    onDeveloperClick = {},
                    onShowcaseAppClick = onShowcaseAppClick,
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                        .padding(contentPadding),
                )
            }
            AppDetailsState.Loading -> { }
            AppDetailsState.Error -> { }
        }
    }
}
