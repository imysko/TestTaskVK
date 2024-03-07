package com.imysko.testtaskvk.ui.screens.catalog

import com.imysko.testtaskvk.domain.usecase.GetProductListUseCase
import com.imysko.testtaskvk.ui.base.BaseViewModel
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

    private val _products = MutableStateFlow<List<ProductUiModel>>(emptyList())
    val products: StateFlow<List<ProductUiModel>>
        get() = _products.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {
        call({
            getProductListUseCase()
        }, onSuccess = { productList ->
            _products.tryEmit(productList.map { it.mapToUiModel() })
        })
    }
}