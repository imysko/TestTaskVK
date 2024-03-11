package com.imysko.testtaskvk.domain.usecase

import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.domain.repositories.ProductsRepository
import javax.inject.Inject

interface GetProductListByCategoryUseCase {

    suspend operator fun invoke(
        category: String,
        limit: Int,
        skip: Int = 0,
    ): Result<List<Product>>
}

internal class GetProductListByCategoryUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository,
) : GetProductListByCategoryUseCase {

    override suspend fun invoke(
        category: String,
        limit: Int,
        skip: Int,
    ): Result<List<Product>> {
        return productsRepository.getProductListByCategory(
            category = category,
            limit = limit,
            skip = skip,
        )
    }
}