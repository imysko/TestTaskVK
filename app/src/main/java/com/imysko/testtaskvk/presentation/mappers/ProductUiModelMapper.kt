package com.imysko.testtaskvk.presentation.mappers

import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.presentation.entities.ProductUiModel
import java.math.RoundingMode

fun Product.mapToUiModel() : ProductUiModel = this.let { from ->
    return ProductUiModel(
        title = from.title,
        description = from.description,
        price = from.price,
        hasDiscount = from.discountPercentage > 0,
        discountPercentage = from.discountPercentage,
        discountPrice = (from.price - from.price * from.discountPercentage / 100)
            .toBigDecimal().setScale(2, RoundingMode.HALF_UP).toFloat(),
        rating = from.rating
            .toBigDecimal().setScale(1, RoundingMode.HALF_UP).toFloat(),
        hasStock = from.stock > 0,
        stock = from.stock,
        brand = from.brand,
        category = from.category,
        thumbnailUrl = from.thumbnailUrl,
        imagesUrl = from.imagesUrl,
    )
}