package com.practicum.vkeducation.presentation.applist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicum.vkeducation.domain.appdetails.AppSummary

@Composable
fun AppListScreen(
    onClickApp: () -> Unit,
    viewModel: AppListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.eventFlow) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is AppListEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    AppListContent(
        appSummaryList = uiState.appSummaryList,
        isLoading = uiState.isLoading,
        sequentialTimeMs = uiState.sequentialTimeMs,
        parallelTimeMs = uiState.parallelTimeMs,
        onClickApp = onClickApp,
        onLoadSequentiallyClick = viewModel::loadRatingsSequentially,
        onLoadInParallelClick = viewModel::loadRatingsInParallel,
        onToolbarClick = viewModel::onToolbarClick,
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun AppListContent(
    appSummaryList: List<AppSummary>,
    isLoading: Boolean,
    sequentialTimeMs: Long?,
    parallelTimeMs: Long?,
    onClickApp: () -> Unit,
    onLoadSequentiallyClick: () -> Unit,
    onLoadInParallelClick: () -> Unit,
    onToolbarClick: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val currentAppIcon = appSummaryList.firstOrNull()?.iconUrl ?: ""
    Scaffold(
        topBar = {
            Toolbar(
                appIconUrl = currentAppIcon,
                onClick = onToolbarClick
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                        Spacer(Modifier.height(8.dp))
                    }
                    if (sequentialTimeMs != null || parallelTimeMs != null) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "Замер времени загрузки рейтингов (10 приложений):",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(Modifier.height(4.dp))
                                if (sequentialTimeMs != null) {
                                    Text(
                                        text = "• Последовательно: $sequentialTimeMs мс",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                if (parallelTimeMs != null) {
                                    Text(
                                        text = "• Параллельно: $parallelTimeMs мс",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onLoadSequentiallyClick,
                            enabled = !isLoading,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Последовательно")
                        }
                        Button(
                            onClick = onLoadInParallelClick,
                            enabled = !isLoading,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Параллельно")
                        }
                    }
                }
            }
            items(appSummaryList) { appSummary ->
                AppSummaryCard(
                    appSummary = appSummary,
                    onClick = onClickApp
                )
            }
        }
    }
}
