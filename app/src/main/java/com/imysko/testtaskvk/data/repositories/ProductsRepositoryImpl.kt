package com.imysko.testtaskvk.data.repositories

import com.imysko.testtaskvk.data.entities.RequestException
import com.imysko.testtaskvk.data.mappers.mapToDomain
import com.imysko.testtaskvk.data.services.ProductsService
import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.domain.repositories.ProductsRepository
import javax.inject.Inject

internal class ProductsRepositoryImpl @Inject constructor(
    private val service: ProductsService,
) : ProductsRepository {

    private var totalProduct: Int? = null
    private var lastQuery: String? = null

    private var lastCategory: String? = null
    private var totalProductInCategory: Int? = null

    override suspend fun getProductList(
        query: String?,
        limit: Int,
        skip: Int,
    ): Result<List<Product>> {
        totalProduct?.let {
            if (it <= skip && query == lastQuery) {
                return Result.success(emptyList())
            }
        }

        lastQuery = query

        val apiResponse = query?.let {
            service.searchProduct(
                query = it,
                limit = limit,
                skip = skip,
            )
        } ?: run {
            service.getProductList(
                limit = limit,
                skip = skip,
            )
        }

        apiResponse.body()?.let { productListResponse ->
            totalProduct = productListResponse.total

            return Result.success(productListResponse.products.map { it.mapToDomain() })
        } ?: run {
            return Result.failure(
                RequestException(
                    statusCode = apiResponse.code(),
                    message = apiResponse.message(),
                )
            )
        }
    }

    override suspend fun getProductListByCategory(
        category: String,
        limit: Int,
        skip: Int,
    ): Result<List<Product>> {
        totalProductInCategory?.let {
            if (it <= skip && category == lastCategory) {
                return Result.success(emptyList())
            }
        }

        lastCategory = category

        val apiResponse = service.getProductListByCategory(
            category = category,
            limit = limit,
            skip = skip,
        )

        apiResponse.body()?.let { productListResponse ->
            totalProductInCategory = productListResponse.total

            return Result.success(productListResponse.products.map { it.mapToDomain() })
        } ?: run {
            return Result.failure(
                RequestException(
                    statusCode = apiResponse.code(),
                    message = apiResponse.message(),
                )
            )
        }
    }
}
