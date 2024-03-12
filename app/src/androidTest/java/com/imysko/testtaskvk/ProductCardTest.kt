package com.imysko.testtaskvk

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.imysko.testtaskvk.presentation.entities.CategoryUiModel
import com.imysko.testtaskvk.presentation.entities.ProductUiModel
import com.imysko.testtaskvk.presentation.ui.components.product.ProductCard
import com.imysko.testtaskvk.presentation.ui.theme.TestTaskVKTheme
import com.imysko.testtaskvk.utils.TestTags
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ProductCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun productCardTest() {
        composeTestRule.setContent {
            TestTaskVKTheme {
                ProductCard(
                    product = product,
                    onClick = { },
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.PRODUCT_CARD_DISCOUNT_PRICE, useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.PRODUCT_CARD_PRICE, useUnmergedTree = true)
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithTag(TestTags.PRODUCT_CARD_OUT_STOCK, useUnmergedTree = true)
            .assertIsNotDisplayed()
    }

    @Test
    fun productCardNoDiscountTest() {
        composeTestRule.setContent {
            TestTaskVKTheme {
                ProductCard(
                    product = productNoDiscount,
                    onClick = { },
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.PRODUCT_CARD_PRICE, useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.PRODUCT_CARD_DISCOUNT_PRICE, useUnmergedTree = true)
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithTag(TestTags.PRODUCT_CARD_OUT_STOCK, useUnmergedTree = true)
            .assertIsNotDisplayed()
    }

    @Test
    fun productCardOutStockTest() {
        composeTestRule.setContent {
            TestTaskVKTheme {
                ProductCard(
                    product = productOutStock,
                    onClick = { },
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.PRODUCT_CARD_PRICE, useUnmergedTree = true)
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithTag(TestTags.PRODUCT_CARD_DISCOUNT_PRICE, useUnmergedTree = true)
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithTag(TestTags.PRODUCT_CARD_OUT_STOCK, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    companion object {
        private val product = ProductUiModel(
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
            category = CategoryUiModel(
                name = "smartphones",
                title = "Smartphones",
            ),
            thumbnailUrl = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            imagesUrl = listOf(
                "https://cdn.dummyjson.com/product-images/1/1.jpg",
                "https://cdn.dummyjson.com/product-images/1/2.jpg",
                "https://cdn.dummyjson.com/product-images/1/3.jpg",
                "https://cdn.dummyjson.com/product-images/1/4.jpg",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            ),
        )

        private val productNoDiscount = ProductUiModel(
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
            category = CategoryUiModel(
                name = "smartphones",
                title = "Smartphones",
            ),
            thumbnailUrl = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            imagesUrl = listOf(
                "https://cdn.dummyjson.com/product-images/1/1.jpg",
                "https://cdn.dummyjson.com/product-images/1/2.jpg",
                "https://cdn.dummyjson.com/product-images/1/3.jpg",
                "https://cdn.dummyjson.com/product-images/1/4.jpg",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            ),
        )

        private val productOutStock = ProductUiModel(
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
            category = CategoryUiModel(
                name = "smartphones",
                title = "Smartphones",
            ),
            thumbnailUrl = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            imagesUrl = listOf(
                "https://cdn.dummyjson.com/product-images/1/1.jpg",
                "https://cdn.dummyjson.com/product-images/1/2.jpg",
                "https://cdn.dummyjson.com/product-images/1/3.jpg",
                "https://cdn.dummyjson.com/product-images/1/4.jpg",
                "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            ),
        )
    }
}
