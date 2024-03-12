package com.imysko.testtaskvk

import com.imysko.testtaskvk.data.MockCategoriesRepository
import com.imysko.testtaskvk.domain.usecase.GetCategoriesListUseCaseImpl
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.Test

internal class GetCategoriesListUseCaseTest {

    @Test
    fun `WHEN categoriesRepository return success result THEN return categories list`() = runTest {

        val categoriesRepository = MockCategoriesRepository(true)
        val useCase = GetCategoriesListUseCaseImpl(categoriesRepository)

        val actualResult = useCase()
        val expectedResult = stubSuccessCategoriesList()

        Assertions.assertThat(actualResult.isFailure).isEqualTo(expectedResult.isFailure)
    }

    @Test
    fun `WHEN categoriesRepository return failure result THEN return isFailure`() = runTest {

        val categoriesRepository = MockCategoriesRepository(false)
        val useCase = GetCategoriesListUseCaseImpl(categoriesRepository)

        val actualResult = useCase()
        val expectedResult = stubFailureCategoriesList()

        Assertions.assertThat(actualResult.isFailure).isEqualTo(expectedResult.isFailure)
    }

    companion object {
        private fun stubSuccessCategoriesList(): Result<List<String>> {
            return Result.success(
                listOf(
                    "smartphones",
                    "laptops",
                    "fragrances",
                    "skincare",
                    "groceries",
                    "home-decoration",
                    "furniture",
                    "tops",
                    "womens-dresses",
                    "womens-shoes",
                    "mens-shirts",
                    "mens-shoes",
                    "mens-watches",
                    "womens-watches",
                    "womens-bags",
                    "womens-jewellery",
                    "sunglasses",
                    "automotive",
                    "motorcycle",
                    "lighting",
                )
            )
        }

        private fun stubFailureCategoriesList(): Result<List<String>> {
            return Result.failure(Throwable("Throwable"))
        }
    }
}