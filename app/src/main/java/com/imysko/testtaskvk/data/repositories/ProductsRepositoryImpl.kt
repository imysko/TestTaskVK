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

    override suspend fun getProductList(
        limit: Int,
        skip: Int,
    ): Result<List<Product>> {
        totalProduct?.let {
            if (it <= skip) {
                return Result.success(emptyList())
            }
        }

        val apiResponse  = service.getProductList(
            limit = limit,
            skip = skip,
        )

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

    override suspend fun searchProduct(
        query: String,
        limit: Int,
        skip: Int,
    ): Result<List<Product>> {
        val apiResponse  = service.searchProduct(
            query = query,
            limit = limit,
            skip = skip,
        )

        apiResponse.body()?.let { productListResponse ->
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
        val apiResponse  = service.getProductListByCategory(
            category = category,
            limit = limit,
            skip = skip,
        )

        apiResponse.body()?.let { productListResponse ->
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