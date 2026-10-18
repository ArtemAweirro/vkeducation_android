package com.practicum.vkeducation.presentation.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.practicum.vkeducation.R

@Composable
internal fun Showcase(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = {
        10
    })

    Column(modifier.fillMaxWidth()) {
        Text(text = stringResource(R.string.showcase))
        HorizontalPager(state = pagerState) {
            // TODO AppCard из AppList
        }
    }
}