package com.imysko.testtaskvk

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.imysko.testtaskvk.presentation.entities.CategoryUiModel
import com.imysko.testtaskvk.presentation.entities.ProductUiModel
import com.imysko.testtaskvk.presentation.ui.productsList.ProductsListContent
import com.imysko.testtaskvk.presentation.ui.productsList.ProductsListUiState
import com.imysko.testtaskvk.presentation.ui.theme.TestTaskVKTheme
import com.imysko.testtaskvk.utils.TestTags
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ProductsListContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showProductsListSateTest() {
        composeTestRule.setContent {
            TestTaskVKTheme {
                ProductsListContent(
                    uiState = ProductsListUiState.ShowProductsList(
                        products = products,
                    ),
                    onProductClick = { },
                    onLoadMore = { },
                    onReloadButtonClick = { },
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.PRODUCTS_LIST).assertIsDisplayed()
    }

    @Test
    fun loadingSateTest() {
        composeTestRule.setContent {
            TestTaskVKTheme {
                ProductsListContent(
                    uiState = ProductsListUiState.OnLoading,
                    onProductClick = { },
                    onLoadMore = { },
                    onReloadButtonClick = { },
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.PRODUCTS_LIST_ON_LOADING).assertIsDisplayed()
    }

    @Test
    fun notFoundSateTest() {
        composeTestRule.setContent {
            TestTaskVKTheme {
                ProductsListContent(
                    uiState = ProductsListUiState.NotFound,
                    onProductClick = { },
                    onLoadMore = { },
                    onReloadButtonClick = { },
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.PRODUCTS_LIST_NOT_FOUND).assertIsDisplayed()
    }

    @Test
    fun errorSateTest() {
        composeTestRule.setContent {
            TestTaskVKTheme {
                ProductsListContent(
                    uiState = ProductsListUiState.OnError,
                    onProductClick = { },
                    onLoadMore = { },
                    onReloadButtonClick = { },
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.PRODUCTS_LIST_ON_ERROR).assertIsDisplayed()
    }

    @Test
    fun noInternetConnectionSateTest() {
        composeTestRule.setContent {
            TestTaskVKTheme {
                ProductsListContent(
                    uiState = ProductsListUiState.NoInternetConnection,
                    onProductClick = { },
                    onLoadMore = { },
                    onReloadButtonClick = { },
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.PRODUCTS_LIST_NO_INTERNET_CONNECTION)
            .assertIsDisplayed()
    }

    companion object {
        private val products = listOf(
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
            ),
        )
    }
}