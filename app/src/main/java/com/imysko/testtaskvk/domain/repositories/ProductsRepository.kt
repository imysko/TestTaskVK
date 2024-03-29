package com.imysko.testtaskvk.domain.repositories

import com.imysko.testtaskvk.domain.entities.Product

interface ProductsRepository {

    suspend fun getProductList(query: String?, limit: Int, skip: Int): Result<List<Product>>

    suspend fun getProductListByCategory(category: String, limit: Int, skip: Int): Result<List<Product>>
}
