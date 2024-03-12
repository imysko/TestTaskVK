package com.imysko.testtaskvk

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.imysko.testtaskvk.presentation.entities.CategoryUiModel
import com.imysko.testtaskvk.presentation.ui.components.categories.CategoriesList
import com.imysko.testtaskvk.presentation.ui.theme.TestTaskVKTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class CategoriesListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun categoriesListTest() {
        composeTestRule.setContent {
            TestTaskVKTheme {
                CategoriesList(
                    categories = categories,
                    onCategoryClick = { },
                )
            }
        }

        categories.forEach { category ->
            composeTestRule.onNodeWithText(category.title).assertIsDisplayed()
        }
    }

    companion object {
        private val categories = listOf(
            CategoryUiModel(
                name = "smartphones",
                title = "Smartphones"
            ),
            CategoryUiModel(
                name = "laptops",
                title = "Laptops"
            ),
            CategoryUiModel(
                name = "home-decoration",
                title = "Home decoration"
            ),
        )
    }
}