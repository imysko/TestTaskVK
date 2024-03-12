package com.imysko.testtaskvk.presentation.ui.screens.categoriesList

import com.imysko.testtaskvk.presentation.entities.CategoryUiModel

sealed class CategoriesUiState {

    data class ShowCategoriesList(
        val categories: List<CategoryUiModel>,
    ) : CategoriesUiState()

    data object OnLoading : CategoriesUiState()

    data object NotFound : CategoriesUiState()

    data object OnError : CategoriesUiState()

    data object NoInternetConnection : CategoriesUiState()
}
