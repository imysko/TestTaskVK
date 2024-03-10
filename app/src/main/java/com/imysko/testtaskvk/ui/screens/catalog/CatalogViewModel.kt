package com.imysko.testtaskvk.ui.screens.catalog

import com.imysko.testtaskvk.domain.usecase.GetProductListUseCase
import com.imysko.testtaskvk.ui.components.base.BaseViewModel
import com.imysko.testtaskvk.ui.entities.ProductUiModel
import com.imysko.testtaskvk.ui.mappers.mapToUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<CatalogUiState>(CatalogUiState.OnLoading)
    val uiState: StateFlow<CatalogUiState>
        get() = _uiState.asStateFlow()

    private val _products = MutableStateFlow<List<ProductUiModel>>(emptyList())
    private val _loadedPageCount = MutableStateFlow(0)

    init {
        reloadProducts()
    }

    private fun getProducts() {
        call({
            getProductListUseCase(
                limit = PAGE_SIZE,
                skip = PAGE_SIZE * _loadedPageCount.value,
            )
        }, onSuccess = { productList ->
            _products.tryEmit(_products.value + productList.map { it.mapToUiModel() })
            _loadedPageCount.tryEmit(_loadedPageCount.value + 1)

            if (_products.value.any()) {
                _uiState.tryEmit(CatalogUiState.MainState(_products.value))
            } else {
                _uiState.tryEmit(CatalogUiState.NotFound)
            }
        }, onError = {
            _uiState.tryEmit(CatalogUiState.OnError)

            _products.tryEmit(emptyList())
            _loadedPageCount.tryEmit(0)
        }, onNetworkUnavailable = {
            _uiState.tryEmit(CatalogUiState.NoInternetConnection)

            _products.tryEmit(emptyList())
            _loadedPageCount.tryEmit(0)
        })
    }

    fun reloadProducts() {
        _uiState.tryEmit(CatalogUiState.OnLoading)

        _products.tryEmit(emptyList())
        _loadedPageCount.tryEmit(0)

        getProducts()
    }

    fun getProductNextPage() {
        _uiState.tryEmit(CatalogUiState.MainState(
            products = _products.value,
            isLoadingNextPage = true,
        ))

        getProducts()
    }

    companion object {
        const val PAGE_SIZE: Int = 20
    }
}
