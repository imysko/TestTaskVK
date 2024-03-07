package com.imysko.testtaskvk.ui.base

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateBefore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imysko.testtaskvk.ui.theme.TestTaskVKTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithReturn(
    title: String,
    onBackClick: (() -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            onBackClick?.let {
                IconButton(
                    onClick = onBackClick,
                    content = {
                        Icon(
                            modifier = Modifier.size(36.dp),
                            imageVector = Icons.AutoMirrored.Outlined.NavigateBefore,
                            contentDescription = "Назад",
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