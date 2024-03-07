package com.imysko.testtaskvk.ui.screens.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ManageSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imysko.testtaskvk.ui.base.TopBarWithReturn
import com.imysko.testtaskvk.ui.entities.ProductUiModel
import com.imysko.testtaskvk.ui.theme.TestTaskVKTheme
import com.imysko.testtaskvk.ui.utils.NavDestinations

@Composable
fun CatalogScreen(
    navController: NavController,
    viewModel: CatalogViewModel = hiltViewModel(),
) {
    val products by viewModel.products.collectAsState()

    CatalogScreen(
        products = products,
        onCategoriesClick = { navController.navigate(NavDestinations.CATEGORIES_SCREEN) },
        onProductClick = { product ->
            navController.navigate("${NavDestinations.PRODUCT_SCREEN}/${product}")
        }
    )
}

@Composable
fun CatalogScreen(
    products: List<ProductUiModel>,
    onCategoriesClick: () -> Unit,
    onProductClick: (ProductUiModel) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarWithReturn(
                title = "Title",
                action = {
                    IconButton(
                        onClick = onCategoriesClick,
                        content = {
                            Icon(
                                modifier = Modifier.size(36.dp),
                                imageVector = Icons.AutoMirrored.Outlined.ManageSearch,
                                contentDescription = "Назад",
                            )
                        },
                    )
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                products.forEach { product ->
                    Text(
                        modifier = Modifier.clickable { onProductClick(product) },
                        text = product.title,
                    )
                }
            }
        },
    )
}

@Composable
@Preview(showBackground = true)
fun CatalogScreenPreview() {
    TestTaskVKTheme {
        CatalogScreen(
            products = emptyList(),
            onCategoriesClick = { },
            onProductClick = { },
        )
    }
}