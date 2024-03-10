package com.imysko.testtaskvk.ui.entities

import android.net.Uri
import com.google.gson.Gson

data class ProductUiModel(
    val title: String,
    val description: String,
    val price: Int,
    val hasDiscount: Boolean,
    val discountPercentage: Float,
    val discountPrice: Float,
    val rating: Float,
    val hasStock: Boolean,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnailUrl: String,
    val imagesUrl: List<String>,
) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}
