package com.imysko.testtaskvk.ui.screens.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.imysko.testtaskvk.R
import com.imysko.testtaskvk.ui.components.base.ErrorPlaceholder
import com.imysko.testtaskvk.ui.components.base.NoInternetConnectionPlaceholder
import com.imysko.testtaskvk.ui.components.product.ProductList
import com.imysko.testtaskvk.ui.entities.ProductUiModel

@Composable
fun ProductsListContent(
    uiState: ProductsListUiState,
    onProductClick: (ProductUiModel) -> Unit,
    onLoadMore: () -> Unit,
    onReloadButtonClick: () -> Unit,
) {
    when (uiState) {
        is ProductsListUiState.ShowProductsList -> {
            ProductList(
                products = uiState.products,
                isLoadingMore = uiState.isLoadingMore,
                onProductClick = onProductClick,
                onLoadMore = onLoadMore,
            )
        }

        ProductsListUiState.OnLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }

        ProductsListUiState.NotFound -> {
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

        ProductsListUiState.OnError -> {
            ErrorPlaceholder(
                onReloadButtonClick = onReloadButtonClick,
            )
        }

        ProductsListUiState.NoInternetConnection -> {
            NoInternetConnectionPlaceholder(
                onReloadButtonClick = onReloadButtonClick,
            )
        }
    }
}
