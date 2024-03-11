package com.imysko.testtaskvk.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.imysko.testtaskvk.ui.entities.navigation.ProductUiModelArgType
import com.imysko.testtaskvk.ui.screens.catalog.CatalogScreen
import com.imysko.testtaskvk.ui.screens.catalog.category.CategoryScreen
import com.imysko.testtaskvk.ui.screens.categories.CategoriesScreen
import com.imysko.testtaskvk.ui.screens.product.ProductScreen
import com.imysko.testtaskvk.ui.utils.NavArguments
import com.imysko.testtaskvk.ui.utils.NavDestinations

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
            route = "${NavDestinations.CATEGORY_SCREEN}/{${NavArguments.CATEGORY}}",
            arguments = listOf(
                navArgument(NavArguments.CATEGORY) {
                    type = NavType.StringType
                },
            ),
        ) {
            CategoryScreen(
                navController = navController,
                categoryTitle = it.arguments?.getString(NavArguments.CATEGORY) ?: "",
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