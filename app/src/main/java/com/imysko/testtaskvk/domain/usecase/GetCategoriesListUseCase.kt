package com.imysko.testtaskvk.domain.usecase

import com.imysko.testtaskvk.domain.entities.Category
import com.imysko.testtaskvk.domain.repositories.CategoriesRepository
import javax.inject.Inject

interface GetCategoriesListUseCase {

    suspend operator fun invoke(): Result<List<Category>>
}

internal class GetCategoriesListUseCaseImpl @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
) : GetCategoriesListUseCase {

    override suspend fun invoke(): Result<List<Category>> {
        return categoriesRepository.getCategoriesList()
    }
}