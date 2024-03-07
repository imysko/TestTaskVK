package com.imysko.testtaskvk.data.services

import retrofit2.Response
import retrofit2.http.GET

interface CategoriesService {

    @GET("/products/categories")
    suspend fun getCategories(): Response<List<String>>
}