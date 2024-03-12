package com.imysko.testtaskvk

import com.imysko.testtaskvk.data.MockProductsRepository
import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.domain.usecase.GetProductListByCategoryUseCaseImpl
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.Test

class GetProductListByCategoryUseCaseTest {

    @Test
    fun `WHEN productsRepository return success result THEN return product list`() = runTest {
        val productsRepository = MockProductsRepository(true)
        val useCase = GetProductListByCategoryUseCaseImpl(productsRepository)

        val actualResult = useCase("laptops", PAGE_SIZE, 0)
        val expectedResult = stubSuccessProductList()

        Assertions.assertThat(actualResult).usingRecursiveComparison().isEqualTo(expectedResult)
    }

    @Test
    fun `WHEN productsRepository return failure result THEN return isFailure`() = runTest {

        val productsRepository = MockProductsRepository(false)
        val useCase = GetProductListByCategoryUseCaseImpl(productsRepository)

        val actualResult = useCase("laptops", PAGE_SIZE, 0)
        val expectedResult = stubFailureProductList()

        Assertions.assertThat(actualResult.isFailure).isEqualTo(expectedResult.isFailure)
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

        private fun stubFailureProductList(): Result<List<Product>> {
            return Result.failure(Throwable("Throwable"))
        }
    }
}