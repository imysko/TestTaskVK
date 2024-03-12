package com.imysko.testtaskvk.domain.repositories

import com.imysko.testtaskvk.domain.entities.Category

interface CategoriesRepository {

    suspend fun getCategoriesList(): Result<List<Category>>
}