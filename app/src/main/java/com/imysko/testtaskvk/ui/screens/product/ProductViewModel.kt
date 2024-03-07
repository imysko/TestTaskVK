package com.imysko.testtaskvk.ui.screens.product

import androidx.lifecycle.SavedStateHandle
import com.google.gson.Gson
import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.ui.base.BaseViewModel
import com.imysko.testtaskvk.ui.utils.NavArguments
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
    private val _product =
        MutableStateFlow(Gson().fromJson(_productString, Product::class.java))
    val product: StateFlow<Product>
        get() = _product.asStateFlow()
}