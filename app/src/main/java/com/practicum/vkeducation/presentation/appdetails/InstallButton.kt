package com.practicum.vkeducation.presentation.appdetails

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practicum.vkeducation.R
import com.practicum.vkeducation.presentation.theme.VkeducationTheme

@Composable
fun InstallButton(
    installState: InstallState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val text = when (installState) {
        is InstallState.Installing -> stringResource(R.string.cancel)
        is InstallState.Installed -> stringResource(R.string.installed)
        InstallState.NotInstalled -> stringResource(R.string.install)
    }

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        modifier = modifier,
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
private fun PreviewNotInstalled() {
    VkeducationTheme {
        InstallButton(
            installState = InstallState.NotInstalled,
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun PreviewInstalling() {
    VkeducationTheme {
        InstallButton(
            installState = InstallState.Installing(45),
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
