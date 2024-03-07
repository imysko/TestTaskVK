package com.imysko.testtaskvk.ui.screens.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imysko.testtaskvk.ui.base.TopBarWithReturn
import com.imysko.testtaskvk.ui.entities.ProductUiModel
import com.imysko.testtaskvk.ui.theme.TestTaskVKTheme

@Composable
fun CategoryScreen(
    navController: NavController,
    categoryTitle: String,
    viewModel: CategoryViewModel = hiltViewModel(),
) {
    val products by viewModel.products.collectAsState()

    CategoryScreen(
        categoryTitle = categoryTitle,
        products = products,
        onBackClick = navController::popBackStack,
    )
}

@Composable
fun CategoryScreen(
    categoryTitle: String,
    products: List<ProductUiModel>,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarWithReturn(
                title = categoryTitle,
                onBackClick = onBackClick,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                products.forEach { product ->
                    Text(text = product.title)
                }
            }
        },
    )
}

@Composable
@Preview(showBackground = true)
fun CategoryScreenPreview() {
    TestTaskVKTheme {
        CategoryScreen(
            categoryTitle = "",
            products = emptyList(),
            onBackClick = { },
        )
    }
}