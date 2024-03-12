package com.imysko.testtaskvk

import com.imysko.testtaskvk.data.MockProductsRepository
import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.domain.usecase.GetProductListUseCaseImpl
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class GetProductListUseCaseTest {

    @Test
    fun `WHEN productsRepository return success result THEN return product list`() = runTest {
        val productsRepository = MockProductsRepository(true)
        val useCase = GetProductListUseCaseImpl(productsRepository)

        val actualResult = useCase("", PAGE_SIZE, 2)
        val expectedResult = stubSuccessProductList()

        assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN productsRepository return success search result THEN return product list`() =
        runTest {
            val productsRepository = MockProductsRepository(true)
            val useCase = GetProductListUseCaseImpl(productsRepository)

            val actualResult = useCase("OLED", PAGE_SIZE, 0)
            val expectedResult = stubSuccessSearchProductList()

            assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult)
        }

    @Test
    fun `WHEN productsRepository return failure result THEN return isFailure`() = runTest {

        val productsRepository = MockProductsRepository(false)
        val useCase = GetProductListUseCaseImpl(productsRepository)

        val actualResult = useCase("", PAGE_SIZE, 0)
        val expectedResult = stubFailureProductList()

        assertThat(actualResult.isFailure).isEqualTo(expectedResult.isFailure)
    }

    companion object {
        private const val PAGE_SIZE = 1

        private fun stubSuccessProductList(): Result<List<Product>> {
            return Result.success(
                listOf(
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
            )
        }

        private fun stubSuccessSearchProductList(): Result<List<Product>> {
            return Result.success(
                listOf(
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
                )
            )
        }

        private fun stubFailureProductList(): Result<List<Product>> {
            return Result.failure(Throwable("Throwable"))
        }
    }
}
