package com.imysko.testtaskvk.ui.components.base

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateBefore
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imysko.testtaskvk.R
import com.imysko.testtaskvk.ui.theme.TestTaskVKTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithReturn(
    title: String,
    onBackClick: (() -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Medium,
                ),
            )
        },
        navigationIcon = {
            onBackClick?.let {
                IconButton(
                    onClick = onBackClick,
                    content = {
                        Icon(
                            modifier = Modifier.size(36.dp),
                            imageVector = Icons.AutoMirrored.Outlined.NavigateBefore,
                            contentDescription = stringResource(id = R.string.back_action),
                        )
                    },
                )
            }
        },
        actions = {
            action?.invoke()
        }
    )
}

@Composable
@Preview(showBackground = true, name = "Top Bar")
fun TopBarWithReturnPreview() {
    TestTaskVKTheme {
        TopBarWithReturn(
            title = "title",
            onBackClick = {},
        )
    }
}
