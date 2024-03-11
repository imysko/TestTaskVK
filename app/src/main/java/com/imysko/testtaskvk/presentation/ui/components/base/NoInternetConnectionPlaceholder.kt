package com.imysko.testtaskvk.presentation.ui.components.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SignalWifiBad
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imysko.testtaskvk.R
import com.imysko.testtaskvk.presentation.ui.theme.TestTaskVKTheme

@Composable
fun NoInternetConnectionPlaceholder(
    onReloadButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
    ) {
        Icon(
            modifier = Modifier.size(120.dp),
            imageVector = Icons.Outlined.SignalWifiBad,
            contentDescription = stringResource(id = R.string.no_internet_connection),
            tint = MaterialTheme.colorScheme.primary,
        )

        OutlinedButton(
            onClick = onReloadButtonClick,
            content = {
                Text(
                    text = stringResource(id = R.string.reload),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
            },
        )
    }
}

@Composable
@Preview
fun NoInternetConnectionPlaceholderPreview() {
    TestTaskVKTheme {
        NoInternetConnectionPlaceholder(
            onReloadButtonClick = { },
        )
    }
}
