package com.imysko.testtaskvk.ui.screens.catalog

import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.ui.entities.ProductUiModel
import com.imysko.testtaskvk.ui.mappers.mapToUiModel
import com.imysko.testtaskvk.ui.screens.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class ProductsListViewModel : BaseViewModel() {

    private val _uiState = MutableStateFlow<ProductsListUiState>(ProductsListUiState.OnLoading)
    val uiState: StateFlow<ProductsListUiState>
        get() = _uiState.asStateFlow()

    private val _products = MutableStateFlow<List<ProductUiModel>>(emptyList())
    protected val loadedPageCount = MutableStateFlow(0)

    private fun getProducts() {
        call(
            useCaseCall = this::useCaseCall,
            onSuccess = this::onSuccessHandle,
            onError = { onErrorHandle() },
            onNetworkUnavailable = this::onNetworkUnavailableHandle,
        )
    }

    protected abstract suspend fun useCaseCall(): Result<List<Product>>

    private fun onSuccessHandle(
        productList: List<Product>,
    ) {
        _products.tryEmit(_products.value + productList.map { it.mapToUiModel() })
        loadedPageCount.tryEmit(loadedPageCount.value + 1)

        if (_products.value.any()) {
            _uiState.tryEmit(ProductsListUiState.ShowProductsList(_products.value))
        } else {
            _uiState.tryEmit(ProductsListUiState.NotFound)
        }
    }

    private fun onErrorHandle() {
        _uiState.tryEmit(ProductsListUiState.OnError)

        _products.tryEmit(emptyList())
        loadedPageCount.tryEmit(0)
    }

    private fun onNetworkUnavailableHandle() {
        _uiState.tryEmit(ProductsListUiState.NoInternetConnection)

        _products.tryEmit(emptyList())
        loadedPageCount.tryEmit(0)
    }

    fun reloadProducts() {
        _uiState.tryEmit(ProductsListUiState.OnLoading)

        _products.tryEmit(emptyList())
        loadedPageCount.tryEmit(0)

        getProducts()
    }

    fun getMoreProducts() {
        _uiState.tryEmit(
            ProductsListUiState.ShowProductsList(
                products = _products.value,
                isLoadingMore = true,
            )
        )

        getProducts()
    }

    companion object {
        const val PAGE_SIZE: Int = 20
    }
}
