package com.imysko.testtaskvk.ui.screens.catalog

import com.imysko.testtaskvk.ui.entities.ProductUiModel

sealed class CatalogUiState {

    data class MainState(
        val products: List<ProductUiModel>,
        val isLoadingNextPage: Boolean = false,
    ) : CatalogUiState()

    data object OnLoading : CatalogUiState()

    data object NotFound : CatalogUiState()

    data object OnError : CatalogUiState()

    data object NoInternetConnection : CatalogUiState()
}
