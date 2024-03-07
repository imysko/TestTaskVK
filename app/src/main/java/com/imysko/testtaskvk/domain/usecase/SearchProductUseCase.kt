package com.imysko.testtaskvk.domain.usecase

import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.domain.repositories.ProductsRepository
import javax.inject.Inject

interface SearchProductUseCase {

    suspend operator fun invoke(
        query: String,
        limit: Int = 20,
        skip: Int = 0,
    ): Result<List<Product>>
}

internal class SearchProductUseCaseImpl @Inject constructor(
    private val productsRepository: ProductsRepository,
) : SearchProductUseCase {

    override suspend fun invoke(
        query: String,
        limit: Int,
        skip: Int,
    ): Result<List<Product>> {
        return productsRepository.searchProduct(
            query = query,
            limit = limit,
            skip = skip,
        )
    }
}