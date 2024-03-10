package com.imysko.testtaskvk.domain.usecase

import androidx.compose.foundation.pager.PageSize
import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.domain.repositories.ProductsRepository
import javax.inject.Inject

interface GetProductListUseCase {

    suspend operator fun invoke(
        limit: Int,
        skip: Int = 0,
    ): Result<List<Product>>
}

internal class GetProductListUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository,
) : GetProductListUseCase {

    override suspend fun invoke(
        limit: Int,
        skip: Int,
    ): Result<List<Product>> {
        return productsRepository.getProductList(
            limit = limit,
            skip = skip,
        )
    }
}