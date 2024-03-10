package com.imysko.testtaskvk.ui.screens.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ManageSearch
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imysko.testtaskvk.R
import com.imysko.testtaskvk.ui.components.base.ErrorPlaceholder
import com.imysko.testtaskvk.ui.components.base.NoInternetConnectionPlaceholder
import com.imysko.testtaskvk.ui.components.base.TopBarWithReturn
import com.imysko.testtaskvk.ui.components.product.ProductList
import com.imysko.testtaskvk.ui.entities.ProductUiModel
import com.imysko.testtaskvk.ui.theme.TestTaskVKTheme
import com.imysko.testtaskvk.ui.utils.NavDestinations

@Composable
fun CatalogScreen(
    navController: NavController,
    viewModel: CatalogViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CatalogScreen(
        uiState = uiState,
        onCategoriesClick = { navController.navigate(NavDestinations.CATEGORIES_SCREEN) },
        onProductClick = { product ->
            navController.navigate("${NavDestinations.PRODUCT_SCREEN}/${product}")
        },
        onLoadMore = viewModel::getProductNextPage,
        onReloadButtonClick = viewModel::reloadProducts,
    )
}

@Composable
fun CatalogScreen(
    uiState: CatalogUiState,
    onCategoriesClick: () -> Unit,
    onProductClick: (ProductUiModel) -> Unit,
    onLoadMore: () -> Unit,
    onReloadButtonClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarWithReturn(
                title = stringResource(id = R.string.catalog_screen_title),
                action = {
                    IconButton(
                        onClick = onCategoriesClick,
                        content = {
                            Icon(
                                modifier = Modifier.size(36.dp),
                                imageVector = Icons.AutoMirrored.Outlined.ManageSearch,
                                contentDescription = stringResource(id = R.string.categories_action),
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
                when (uiState) {
                    is CatalogUiState.MainState -> {
                        ProductList(
                            products = uiState.products,
                            onProductClick = onProductClick,
                            onLoadMore = onLoadMore,
                        )
                    }
                    CatalogUiState.OnLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }
                    CatalogUiState.NotFound -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = stringResource(id = R.string.not_found),
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                ),
                            )
                        }
                    }
                    CatalogUiState.OnError -> {
                        ErrorPlaceholder(
                            onReloadButtonClick = onReloadButtonClick,
                        )
                    }
                    CatalogUiState.NoInternetConnection -> {
                        NoInternetConnectionPlaceholder(
                            onReloadButtonClick = onReloadButtonClick,
                        )
                    }
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
            uiState = CatalogUiState.NoInternetConnection,
            onCategoriesClick = { },
            onProductClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
        )
    }
}
