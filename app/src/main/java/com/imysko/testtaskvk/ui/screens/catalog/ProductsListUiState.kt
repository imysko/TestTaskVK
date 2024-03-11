package com.imysko.testtaskvk.ui.screens.catalog

import com.imysko.testtaskvk.ui.entities.ProductUiModel

sealed class ProductsListUiState {

    data class ShowProductsList(
        val products: List<ProductUiModel>,
        val isLoadingMore: Boolean = false,
    ) : ProductsListUiState()

    data object OnLoading : ProductsListUiState()

    data object NotFound : ProductsListUiState()

    data object OnError : ProductsListUiState()

    data object NoInternetConnection : ProductsListUiState()
}
