package com.imysko.testtaskvk.data

import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.domain.repositories.ProductsRepository

internal class MockProductsRepository(
    private val isSuccessResult: Boolean,
) : ProductsRepository {

    override suspend fun getProductList(
        query: String?,
        limit: Int,
        skip: Int,
    ): Result<List<Product>> {
        return if (isSuccessResult) {
            Result.success(
                mockProductList()
                    .filter { product ->
                        query?.let {
                            product.title.lowercase().contains(it.lowercase())
                                    || product.description.lowercase().contains(it.lowercase())
                        } ?: true
                    }
                    .drop(skip)
                    .take(limit)
            )
        } else {
            Result.failure(Throwable("Throwable"))
        }
    }

    override suspend fun getProductListByCategory(
        category: String,
        limit: Int,
        skip: Int,
    ): Result<List<Product>> {
        return if (isSuccessResult) {
            Result.success(
                mockProductList()
                    .filter { it.category == category }
                    .drop(skip)
                    .take(limit)
            )
        } else {
            Result.failure(Throwable("Throwable"))
        }
    }

    private fun mockProductList(): List<Product> {
        return listOf(
            Product(
                title = "iPhone 9",
                description = "An apple mobile which is nothing like apple",
                price = 549,
                discountPercentage = 12.96f,
                rating = 3.7f,
                stock = 94,
                brand = "Apple",
                category = "smartphones",
                thumbnailUrl = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
                imagesUrl = listOf(
                    "https://cdn.dummyjson.com/product-images/1/1.jpg",
                    "https://cdn.dummyjson.com/product-images/1/2.jpg",
                    "https://cdn.dummyjson.com/product-images/1/3.jpg",
                    "https://cdn.dummyjson.com/product-images/1/4.jpg",
                    "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
                ),
            ),
            Product(
                title = "iPhone X",
                description = "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
                price = 899,
                discountPercentage = 0f,
                rating = 4.7f,
                stock = 2,
                brand = "Apple",
                category = "smartphones",
                thumbnailUrl = "https://cdn.dummyjson.com/product-images/2/thumbnail.jpg",
                imagesUrl = listOf(
                    "https://cdn.dummyjson.com/product-images/2/1.jpg",
                    "https://cdn.dummyjson.com/product-images/2/2.jpg",
                    "https://cdn.dummyjson.com/product-images/2/3.jpg",
                    "https://cdn.dummyjson.com/product-images/2/thumbnail.jpg",
                ),
            ),
            Product(
                title = "MacBook Pro",
                description = "MacBook Pro 2021 with mini-LED display may launch between September, November",
                price = 1749,
                discountPercentage = 11.02f,
                rating = 4.3f,
                stock = 0,
                brand = "Apple",
                category = "laptops",
                thumbnailUrl = "https://cdn.dummyjson.com/product-images/6/thumbnail.png",
                imagesUrl = listOf(
                    "https://cdn.dummyjson.com/product-images/6/1.png",
                    "https://cdn.dummyjson.com/product-images/6/2.jpg",
                    "https://cdn.dummyjson.com/product-images/6/3.png",
                    "https://cdn.dummyjson.com/product-images/6/4.jpg",
                ),
            ),
        )
    }
}
