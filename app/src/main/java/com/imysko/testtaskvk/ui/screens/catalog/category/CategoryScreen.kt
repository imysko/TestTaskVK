package com.imysko.testtaskvk.ui.screens.catalog.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imysko.testtaskvk.ui.components.base.TopBarWithReturn
import com.imysko.testtaskvk.ui.entities.ProductUiModel
import com.imysko.testtaskvk.ui.screens.catalog.ProductsListContent
import com.imysko.testtaskvk.ui.screens.catalog.ProductsListUiState
import com.imysko.testtaskvk.ui.theme.TestTaskVKTheme
import com.imysko.testtaskvk.ui.utils.NavDestinations
import com.imysko.testtaskvk.ui.utils.preview.ProductsListPreviewParameterProvider

@Composable
fun CategoryScreen(
    navController: NavController,
    categoryTitle: String,
    viewModel: CategoryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CategoryScreen(
        categoryTitle = categoryTitle,
        uiState = uiState,
        onBackClick = navController::popBackStack,
        onProductClick = { product ->
            navController.navigate("${NavDestinations.PRODUCT_SCREEN}/${product}")
        },
        onLoadMore = viewModel::getMoreProducts,
        onReloadButtonClick = viewModel::reloadProducts,
    )
}

@Composable
fun CategoryScreen(
    categoryTitle: String,
    uiState: ProductsListUiState,
    onBackClick: () -> Unit,
    onProductClick: (ProductUiModel) -> Unit,
    onLoadMore: () -> Unit,
    onReloadButtonClick: () -> Unit,
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
@Preview(showBackground = true, name = "Category list state")
fun CategoryScreenPreview(
    @PreviewParameter(ProductsListPreviewParameterProvider::class) parameter: List<ProductUiModel>,
) {
    TestTaskVKTheme {
        CategoryScreen(
            categoryTitle = "smartphones",
            uiState = ProductsListUiState.ShowProductsList(
                products = parameter,
                isLoadingMore = true,
            ),
            onBackClick = { },
            onProductClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Category loading state")
fun CategoryScreenLoadingPreview() {
    TestTaskVKTheme {
        CategoryScreen(
            categoryTitle = "smartphones",
            uiState = ProductsListUiState.OnLoading,
            onBackClick = { },
            onProductClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Category not found state")
fun CategoryScreenNotFoundPreview() {
    TestTaskVKTheme {
        CategoryScreen(
            categoryTitle = "smartphones",
            uiState = ProductsListUiState.NotFound,
            onBackClick = { },
            onProductClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Category error state")
fun CategoryScreenErrorPreview() {
    TestTaskVKTheme {
        CategoryScreen(
            categoryTitle = "smartphones",
            uiState = ProductsListUiState.OnError,
            onBackClick = { },
            onProductClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Category no internet connection state")
fun CategoryScreenNoInternetConnectionPreview() {
    TestTaskVKTheme {
        CategoryScreen(
            categoryTitle = "smartphones",
            uiState = ProductsListUiState.NoInternetConnection,
            onBackClick = { },
            onProductClick = { },
            onLoadMore = { },
            onReloadButtonClick = { },
        )
    }
}
