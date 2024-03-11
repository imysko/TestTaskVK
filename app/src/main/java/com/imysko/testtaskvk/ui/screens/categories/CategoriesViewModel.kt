package com.imysko.testtaskvk.ui.screens.categories

import com.imysko.testtaskvk.domain.usecase.GetCategoriesListUseCase
import com.imysko.testtaskvk.ui.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesListUseCase: GetCategoriesListUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<CategoriesUiState>(CategoriesUiState.OnLoading)
    val uiState: StateFlow<CategoriesUiState>
        get() = _uiState.asStateFlow()

    init {
        getCategories()
    }

    fun getCategories() {
        _uiState.tryEmit(CategoriesUiState.OnLoading)

        call(
            useCaseCall = {
                getCategoriesListUseCase()
            },
            onSuccess = { categoriesList ->
                if (categoriesList.any()) {
                    _uiState.tryEmit(CategoriesUiState.ShowCategoriesList(categoriesList))
                } else {
                    _uiState.tryEmit(CategoriesUiState.NotFound)
                }
            },
            onError = {
                _uiState.tryEmit(CategoriesUiState.OnError)
            },
            onNetworkUnavailable = {
                _uiState.tryEmit(CategoriesUiState.NoInternetConnection)
            },
        )
    }
}
