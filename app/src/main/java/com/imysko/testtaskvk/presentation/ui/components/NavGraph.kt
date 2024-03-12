package com.imysko.testtaskvk.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.imysko.testtaskvk.presentation.entities.navigation.CategoryUiModelArgType
import com.imysko.testtaskvk.presentation.entities.navigation.NavArguments
import com.imysko.testtaskvk.presentation.entities.navigation.NavDestinations
import com.imysko.testtaskvk.presentation.entities.navigation.ProductUiModelArgType
import com.imysko.testtaskvk.presentation.ui.screens.catalog.CatalogScreen
import com.imysko.testtaskvk.presentation.ui.screens.categoriesList.CategoriesScreen
import com.imysko.testtaskvk.presentation.ui.screens.category.CategoryScreen
import com.imysko.testtaskvk.presentation.ui.screens.product.ProductScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavDestinations.CATALOG_SCREEN,
    ) {

        composable(route = NavDestinations.CATALOG_SCREEN) {
            CatalogScreen(navController)
        }
        
        composable(route = NavDestinations.CATEGORIES_SCREEN) {
            CategoriesScreen(navController)
        }

        composable(
            route = "${NavDestinations.CATEGORY_SCREEN}/{${NavArguments.CATEGORY}}/" +
                    "{${NavArguments.CATEGORY_TITLE}}",
            arguments = listOf(
                navArgument(NavArguments.CATEGORY) {
                    type = CategoryUiModelArgType()
                },
                navArgument(NavArguments.CATEGORY_TITLE) {
                    type = NavType.StringType
                }
            ),
        ) {
            CategoryScreen(
                navController = navController,
                categoryTitle = it.arguments?.getString(NavArguments.CATEGORY_TITLE) ?: "",
            )
        }
        
        composable(
            route = "${NavDestinations.PRODUCT_SCREEN}/{${NavArguments.PRODUCT}}",
            arguments = listOf(
                navArgument(NavArguments.PRODUCT) {
                    type = ProductUiModelArgType()
                },
            ),
        ) {
            ProductScreen(navController)
        }
    }
}