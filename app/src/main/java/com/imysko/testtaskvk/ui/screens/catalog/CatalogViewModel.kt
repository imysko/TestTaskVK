package com.imysko.testtaskvk.ui.screens.catalog

import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.domain.usecase.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
) : ProductsListViewModel() {

    init {
        reloadProducts()
    }

    override suspend fun useCaseCall(): Result<List<Product>> {
        return getProductListUseCase(
            query = "",
            limit = PAGE_SIZE,
            skip = PAGE_SIZE * loadedPageCount.value,
        )
    }
}
