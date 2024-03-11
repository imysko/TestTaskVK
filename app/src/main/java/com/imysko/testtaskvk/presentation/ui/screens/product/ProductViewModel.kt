package com.imysko.testtaskvk.presentation.ui.screens.product

import androidx.lifecycle.SavedStateHandle
import com.google.gson.Gson
import com.imysko.testtaskvk.presentation.entities.ProductUiModel
import com.imysko.testtaskvk.presentation.entities.navigation.NavArguments
import com.imysko.testtaskvk.presentation.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _productString: String = checkNotNull(savedStateHandle[NavArguments.PRODUCT])
    private val _product = MutableStateFlow(getProductDataFromJson())

    val product: StateFlow<ProductUiModel>
        get() = _product.asStateFlow()

    private fun getProductDataFromJson(): ProductUiModel {
        return Gson().fromJson(_productString, ProductUiModel::class.java)
    }
}
