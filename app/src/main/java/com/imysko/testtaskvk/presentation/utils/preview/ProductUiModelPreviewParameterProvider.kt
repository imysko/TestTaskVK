package com.imysko.testtaskvk.presentation.utils.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.imysko.testtaskvk.presentation.entities.ProductUiModel

class ProductUiModelPreviewParameterProvider :
    PreviewParameterProvider<ProductUiModel> {

    override val values = sequenceOf(
        ProductUiModel(
            title = "iPhone 9",
            description = "An apple mobile which is nothing like apple",
            price = 549,
            hasDiscount = true,
            discountPercentage = 12.96f,
            discountPrice = 477.85f,
            rating = 4.7f,
            hasStock = true,
            stock = 94,
            brand = "Apple",
            category = "smartphones",
            thumbnailUrl = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            imagesUrl = listOf(
                "https://cdn.dummyjson.com/product-images/1/1.jpg",
                "https://cdn.dummyjson.com/product-images/1/2.jpg",
                "https://cdn.dummyjson.com/product-images/1/3.jpg",
                "https://cdn.dummyjson.com/product-images/1/4.jpg",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            ),
        ),
        ProductUiModel(
            title = "iPhone 9",
            description = "An apple mobile which is nothing like apple",
            price = 549,
            hasDiscount = false,
            discountPercentage = 0f,
            discountPrice = 549f,
            rating = 4.7f,
            hasStock = true,
            stock = 94,
            brand = "Apple",
            category = "smartphones",
            thumbnailUrl = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            imagesUrl = listOf(
                "https://cdn.dummyjson.com/product-images/1/1.jpg",
                "https://cdn.dummyjson.com/product-images/1/2.jpg",
                "https://cdn.dummyjson.com/product-images/1/3.jpg",
                "https://cdn.dummyjson.com/product-images/1/4.jpg",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            ),
        ),
        ProductUiModel(
            title = "iPhone 9",
            description = "An apple mobile which is nothing like apple",
            price = 549,
            hasDiscount = true,
            discountPercentage = 12.96f,
            discountPrice = 477.85f,
            rating = 4.7f,
            hasStock = false,
            stock = 0,
            brand = "Apple",
            category = "smartphones",
            thumbnailUrl = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            imagesUrl = listOf(
                "https://cdn.dummyjson.com/product-images/1/1.jpg",
                "https://cdn.dummyjson.com/product-images/1/2.jpg",
                "https://cdn.dummyjson.com/product-images/1/3.jpg",
                "https://cdn.dummyjson.com/product-images/1/4.jpg",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            ),
        ),
    )
}
