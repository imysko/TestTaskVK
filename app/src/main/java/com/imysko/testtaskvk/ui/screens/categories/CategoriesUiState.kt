package com.imysko.testtaskvk.ui.screens.categories

sealed class CategoriesUiState {

    data class MainState(
        val categories: List<String>,
    ) : CategoriesUiState()

    data object OnLoading : CategoriesUiState()

    data object NotFound : CategoriesUiState()

    data object OnError : CategoriesUiState()

    data object NoInternetConnection : CategoriesUiState()
}
