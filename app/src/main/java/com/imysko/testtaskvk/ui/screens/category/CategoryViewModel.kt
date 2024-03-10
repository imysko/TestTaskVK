package com.imysko.testtaskvk.ui.screens.category

import androidx.lifecycle.SavedStateHandle
import com.imysko.testtaskvk.domain.usecase.GetProductListByCategoryUseCase
import com.imysko.testtaskvk.ui.components.base.BaseViewModel
import com.imysko.testtaskvk.ui.entities.ProductUiModel
import com.imysko.testtaskvk.ui.mappers.mapToUiModel
import com.imysko.testtaskvk.ui.utils.NavArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductListByCategoryUseCase: GetProductListByCategoryUseCase,
) : BaseViewModel() {

    private val _category: String = checkNotNull(savedStateHandle[NavArguments.CATEGORY])

    private val _products = MutableStateFlow<List<ProductUiModel>>(emptyList())
    val products: StateFlow<List<ProductUiModel>>
        get() = _products.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {
        call({
            getProductListByCategoryUseCase(
                category = _category,
            )
        }, onSuccess = { productList ->
            _products.tryEmit(productList.map { it.mapToUiModel() })
        })
    }
}
