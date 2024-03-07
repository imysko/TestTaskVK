package com.imysko.testtaskvk.ui.mappers

import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.ui.entities.ProductUiModel

fun Product.mapToUiModel() : ProductUiModel = this.let { from ->
    return ProductUiModel(
        title = from.title,
        description = from.description,
        price = from.price,
        discountPercentage = from.discountPercentage,
        discountPrice = from.price - from.price * from.discountPercentage,
        rating = from.rating,
        stock = from.stock,
        brand = from.brand,
        category = from.category,
    )
}