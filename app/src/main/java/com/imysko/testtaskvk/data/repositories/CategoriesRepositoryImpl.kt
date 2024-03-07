package com.imysko.testtaskvk.data.repositories

import com.imysko.testtaskvk.data.entities.RequestException
import com.imysko.testtaskvk.data.services.CategoriesService
import com.imysko.testtaskvk.domain.repositories.CategoriesRepository
import javax.inject.Inject

internal class CategoriesRepositoryImpl @Inject constructor(
    private val service: CategoriesService,
) : CategoriesRepository {

    override suspend fun getCategoriesList(): Result<List<String>> {
        val apiResponse  = service.getCategories()

        apiResponse.body()?.let { categoriesList ->
            return Result.success(categoriesList)
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