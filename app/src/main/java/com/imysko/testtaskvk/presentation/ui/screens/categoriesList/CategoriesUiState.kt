package com.imysko.testtaskvk.presentation.ui.screens.categoriesList

sealed class CategoriesUiState {

    data class ShowCategoriesList(
        val categories: List<String>,
    ) : CategoriesUiState()

    data object OnLoading : CategoriesUiState()

    data object NotFound : CategoriesUiState()

    data object OnError : CategoriesUiState()

    data object NoInternetConnection : CategoriesUiState()
}
