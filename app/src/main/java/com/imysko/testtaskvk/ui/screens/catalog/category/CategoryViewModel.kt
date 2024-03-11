package com.imysko.testtaskvk.ui.screens.catalog.category

import androidx.lifecycle.SavedStateHandle
import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.domain.usecase.GetProductListByCategoryUseCase
import com.imysko.testtaskvk.ui.screens.catalog.ProductsListViewModel
import com.imysko.testtaskvk.ui.utils.NavArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getProductListByCategoryUseCase: GetProductListByCategoryUseCase,
) : ProductsListViewModel() {

    private val _category: String = checkNotNull(savedStateHandle[NavArguments.CATEGORY])

    init {
        reloadProducts()
    }

    override suspend fun useCaseCall(): Result<List<Product>> {
        return getProductListByCategoryUseCase(
            category = _category,
            limit = PAGE_SIZE,
            skip = PAGE_SIZE * loadedPageCount.value,
        )
    }
}
