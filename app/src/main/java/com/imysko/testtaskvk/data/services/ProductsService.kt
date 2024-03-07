package com.imysko.testtaskvk.data.services

import com.imysko.testtaskvk.data.entities.ProductListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsService {

    @GET("/products")
    suspend fun getProductList(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
    ): Response<ProductListResponse>

    @GET("/products/search")
    suspend fun searchProduct(
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
    ): Response<ProductListResponse>

    @GET("/products/category/{category}")
    suspend fun getProductListByCategory(
        @Path("category") category: String,
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
    ): Response<ProductListResponse>
}