package com.imysko.testtaskvk.domain.usecase

import com.imysko.testtaskvk.domain.repositories.CategoriesRepository
import javax.inject.Inject

interface GetCategoriesListUseCase {

    suspend operator fun invoke(): Result<List<String>>
}

internal class GetCategoriesListUseCaseImpl @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
) : GetCategoriesListUseCase {

    override suspend fun invoke(): Result<List<String>> {
        return categoriesRepository.getCategoriesList()
    }
}