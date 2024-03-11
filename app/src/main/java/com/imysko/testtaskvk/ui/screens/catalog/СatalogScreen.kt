package com.imysko.testtaskvk.ui.screens.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ManageSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imysko.testtaskvk.R
import com.imysko.testtaskvk.ui.components.base.SearchLine
import com.imysko.testtaskvk.ui.components.base.TopBarWithReturn
import com.imysko.testtaskvk.ui.entities.ProductUiModel
import com.imysko.testtaskvk.ui.theme.TestTaskVKTheme
import com.imysko.testtaskvk.ui.utils.NavDestinations
import com.imysko.testtaskvk.ui.utils.preview.ProductsListPreviewParameterProvider

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
        onLoadMore = viewModel::getMoreProducts,
        onReloadButtonClick = viewModel::reloadProducts,
        onSearchDone = viewModel::searchProduct,
    )
}

@Composable
fun CatalogScreen(
    uiState: ProductsListUiState,
    onCategoriesClick: () -> Unit,
    onProductClick: (ProductUiModel) -> Unit,
    onLoadMore: () -> Unit,
    onReloadButtonClick: () -> Unit,
    onSearchDone: (String) -> Unit,
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
                SearchLine(
                    onSearchDone = onSearchDone,
                )

                ProductsListContent(
                    uiState = uiState,
                    onProductClick = onProductClick,
                    onLoadMore = onLoadMore,
                    onReloadButtonClick = onReloadButtonClick,
                )
            }
        },
    )
}

@Composable
@Preview(showBackground = true, name = "Catalog list state")
fun CatalogScreenPreview(
    @PreviewParameter(ProductsListPreviewParameterProvider::class) parameter: List<ProductUiModel>,
) {
    TestTaskVKTheme {
        CatalogScreen(
            uiState = ProductsListUiState.ShowProductsList(
                products = parameter,
                isLoadingMore = true,
            ),
            onCategoriesClick = { },
            onProductClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
            onSearchDone = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Catalog loading state")
fun CatalogScreenLoadingPreview() {
    TestTaskVKTheme {
        CatalogScreen(
            uiState = ProductsListUiState.OnLoading,
            onCategoriesClick = { },
            onProductClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
            onSearchDone = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Catalog not found state")
fun CatalogScreenNotFoundPreview() {
    TestTaskVKTheme {
        CatalogScreen(
            uiState = ProductsListUiState.NotFound,
            onCategoriesClick = { },
            onProductClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
            onSearchDone = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Catalog error state")
fun CatalogScreenErrorPreview() {
    TestTaskVKTheme {
        CatalogScreen(
            uiState = ProductsListUiState.OnError,
            onCategoriesClick = { },
            onProductClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
            onSearchDone = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Catalog no internet connection state")
fun CatalogScreenNoInternetConnectionPreview() {
    TestTaskVKTheme {
        CatalogScreen(
            uiState = ProductsListUiState.NoInternetConnection,
            onCategoriesClick = { },
            onProductClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
            onSearchDone = { },
        )
    }
}
