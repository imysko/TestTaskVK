package com.imysko.testtaskvk.ui.screens.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateNext
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imysko.testtaskvk.ui.base.TopBarWithReturn
import com.imysko.testtaskvk.ui.theme.TestTaskVKTheme
import com.imysko.testtaskvk.ui.utils.NavDestinations

@Composable
fun CategoriesScreen(
    navController: NavController,
    viewModel: CategoriesViewModel = hiltViewModel(),
) {
    val categories by viewModel.categories.collectAsState()

    CategoriesScreen(
        categories = categories,
        onBackClick = navController::popBackStack,
        onCategoryClick = { category ->
            navController.navigate("${NavDestinations.CATEGORY_SCREEN}/${category}")
        },
    )
}

@Composable
fun CategoriesScreen(
    categories: List<String>,
    onBackClick: () -> Unit,
    onCategoryClick: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarWithReturn(
                title = "Категории",
                onBackClick = onBackClick,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                LazyColumn {
                    itemsIndexed(categories) { index, category ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onCategoryClick(category) }
                                .padding(vertical = 10.dp, horizontal = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = category)

                            Icon(
                                modifier = Modifier.size(36.dp),
                                imageVector = Icons.AutoMirrored.Outlined.NavigateNext,
                                contentDescription = "Вперёд",
                            )
                        }

                        if (index < categories.lastIndex) {
                            HorizontalDivider(thickness = 2.dp)
                        }
                    }
                }
            }
        },
    )
}

@Composable
@Preview(showBackground = true)
fun CategoriesScreenPreview() {
    TestTaskVKTheme {
        CategoriesScreen(
            categories = listOf("first", "second"),
            onBackClick = { },
            onCategoryClick = { },
        )
    }
}