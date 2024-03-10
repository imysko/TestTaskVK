package com.imysko.testtaskvk.domain.entities

data class Product(
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnailUrl: String,
    val imagesUrl: List<String>,
)
