package com.imysko.testtaskvk.ui.screens.categories

import com.imysko.testtaskvk.ui.entities.ProductUiModel
import com.imysko.testtaskvk.ui.screens.catalog.CatalogUiState

sealed class CategoriesUiState {

    data class MainState(
        val categories: List<String>,
    ) : CategoriesUiState()

    data object OnLoading : CategoriesUiState()

    data object NotFound : CategoriesUiState()

    data object OnError : CategoriesUiState()

    data object NoInternetConnection : CategoriesUiState()
}
