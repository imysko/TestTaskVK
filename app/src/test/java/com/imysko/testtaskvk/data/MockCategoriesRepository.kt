package com.imysko.testtaskvk.data

import com.imysko.testtaskvk.data.mappers.mapToDomainCategory
import com.imysko.testtaskvk.domain.entities.Category
import com.imysko.testtaskvk.domain.repositories.CategoriesRepository

internal class MockCategoriesRepository(
    private val isSuccessResult: Boolean,
) : CategoriesRepository {

    override suspend fun getCategoriesList(): Result<List<Category>> {
        return if (isSuccessResult) {
            Result.success(mockCategoriesList().map { it.mapToDomainCategory() })
        } else {
            Result.failure(Throwable("Throwable"))
        }
    }

    private fun mockCategoriesList(): List<String> {
        return listOf(
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
    }
}
