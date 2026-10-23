package com.practicum.vkeducation.presentation.applist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicum.vkeducation.domain.appdetails.AppSummary

@Composable
fun AppListScreen(
    onClickApp: () -> Unit,
    viewModel: AppListViewModel = viewModel()
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
        onClickApp = onClickApp,
        onToolbarClick = viewModel::onToolbarClick,
        snackbarHostState = snackbarHostState
    )
}

@Composable
fun AppListContent(
    appSummaryList: List<AppSummary>,
    onClickApp: () -> Unit,
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
            items(appSummaryList) { appSummary ->
                AppSummaryCard(
                    appSummary = appSummary,
                    onClick = onClickApp
                )
            }
        }
    }
}
