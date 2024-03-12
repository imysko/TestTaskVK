package com.imysko.testtaskvk.data.mappers

import com.imysko.testtaskvk.data.entities.ProductResponse
import com.imysko.testtaskvk.domain.entities.Product

fun ProductResponse.mapToDomain() : Product = this.let { from ->
    return Product(
        title = from.title,
        description = from.description,
        price = from.price,
        discountPercentage = from.discountPercentage,
        rating = from.rating,
        stock = from.stock,
        brand = from.brand,
        category = from.category.mapToDomainCategory(),
        thumbnailUrl = from.thumbnail,
        imagesUrl = from.images,
    )
}
