package com.imysko.testtaskvk.domain.entities

import android.net.Uri
import com.google.gson.Gson

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
