package com.imysko.testtaskvk.data.entities

import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    @SerializedName("products")
    val products: List<ProductResponse>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int,
)
