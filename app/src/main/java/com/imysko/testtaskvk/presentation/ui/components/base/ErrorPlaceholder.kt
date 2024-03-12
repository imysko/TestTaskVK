package com.imysko.testtaskvk.presentation.ui.components.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Report
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
fun ErrorPlaceholder(
    modifier: Modifier = Modifier,
    onReloadButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Outlined.Report,
                contentDescription = stringResource(id = R.string.error),
                tint = MaterialTheme.colorScheme.primary,
            )

            Text(
                text = stringResource(id = R.string.error),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
            )
        }

        OutlinedButton(
            onClick = onReloadButtonClick,
            content = {
                Text(
                    text = stringResource(id = R.string.try_again),
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
fun ErrorPlaceholderPreview() {
    TestTaskVKTheme {
        ErrorPlaceholder(
            onReloadButtonClick = { },
        )
    }
}
