package com.imysko.testtaskvk.presentation.ui.screens.category

import androidx.lifecycle.SavedStateHandle
import com.google.gson.Gson
import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.domain.usecase.GetProductListByCategoryUseCase
import com.imysko.testtaskvk.presentation.entities.CategoryUiModel
import com.imysko.testtaskvk.presentation.entities.navigation.NavArguments
import com.imysko.testtaskvk.presentation.ui.productsList.ProductsListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductListByCategoryUseCase: GetProductListByCategoryUseCase,
) : ProductsListViewModel() {

    private val _categoryString: String = checkNotNull(savedStateHandle[NavArguments.CATEGORY])
    private val _category = MutableStateFlow(getCategoryDataFromJson())

    init {
        reloadProducts()
    }

    private fun getCategoryDataFromJson(): CategoryUiModel {
        return Gson().fromJson(_categoryString, CategoryUiModel::class.java)
    }

    override suspend fun useCaseCall(): Result<List<Product>> {
        return getProductListByCategoryUseCase(
            category = _category.value.name,
            limit = PAGE_SIZE,
            skip = PAGE_SIZE * loadedPageCount.value,
        )
    }
}
