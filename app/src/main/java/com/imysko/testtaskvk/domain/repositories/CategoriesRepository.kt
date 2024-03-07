package com.imysko.testtaskvk.domain.repositories

interface CategoriesRepository {

    suspend fun getCategoriesList(): Result<List<String>>
}