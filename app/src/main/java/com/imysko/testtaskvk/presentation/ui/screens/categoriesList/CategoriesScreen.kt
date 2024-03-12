package com.imysko.testtaskvk.presentation.ui.screens.categoriesList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imysko.testtaskvk.R
import com.imysko.testtaskvk.presentation.entities.CategoryUiModel
import com.imysko.testtaskvk.presentation.entities.navigation.NavDestinations
import com.imysko.testtaskvk.presentation.ui.components.base.ErrorPlaceholder
import com.imysko.testtaskvk.presentation.ui.components.base.NoInternetConnectionPlaceholder
import com.imysko.testtaskvk.presentation.ui.components.base.TopBarWithReturn
import com.imysko.testtaskvk.presentation.ui.components.categories.CategoriesList
import com.imysko.testtaskvk.presentation.ui.theme.TestTaskVKTheme
import com.imysko.testtaskvk.presentation.utils.preview.CategoriesListPreviewParameterProvider

@Composable
fun CategoriesScreen(
    navController: NavController,
    viewModel: CategoriesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CategoriesScreen(
        uiState = uiState,
        onBackClick = navController::popBackStack,
        onCategoryClick = { category ->
            navController.navigate("${NavDestinations.CATEGORY_SCREEN}/${category}/${category.title}")
        },
        onReloadButtonClick = viewModel::getCategories,
    )
}

@Composable
fun CategoriesScreen(
    uiState: CategoriesUiState,
    onBackClick: () -> Unit,
    onCategoryClick: (CategoryUiModel) -> Unit,
    onReloadButtonClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarWithReturn(
                title = stringResource(id = R.string.categories_title),
                onBackClick = onBackClick,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                when (uiState) {
                    is CategoriesUiState.ShowCategoriesList -> {
                        CategoriesList(
                            categories = uiState.categories,
                            onCategoryClick = onCategoryClick,
                        )
                    }
                    CategoriesUiState.OnLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }
                    CategoriesUiState.NotFound -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = stringResource(id = R.string.not_found),
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.SemiBold,
                                ),
                            )
                        }
                    }
                    CategoriesUiState.OnError -> {
                        ErrorPlaceholder(
                            onReloadButtonClick = onReloadButtonClick,
                        )
                    }
                    CategoriesUiState.NoInternetConnection -> {
                        NoInternetConnectionPlaceholder(
                            onReloadButtonClick = onReloadButtonClick,
                        )
                    }
                }
            }
        },
    )
}

@Composable
@Preview(showBackground = true, name = "Categories list")
fun CategoriesScreenPreview(
    @PreviewParameter(CategoriesListPreviewParameterProvider::class) parameter: List<CategoryUiModel>,
) {
    TestTaskVKTheme {
        CategoriesScreen(
            uiState = CategoriesUiState.ShowCategoriesList(
                categories = parameter,
            ),
            onBackClick = { },
            onCategoryClick = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Categories loading state")
fun CategoriesScreenLoadingPreview() {
    TestTaskVKTheme {
        CategoriesScreen(
            uiState = CategoriesUiState.OnLoading,
            onBackClick = { },
            onCategoryClick = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Categories not found state")
fun CategoriesScreenNotFoundPreview() {
    TestTaskVKTheme {
        CategoriesScreen(
            uiState = CategoriesUiState.NotFound,
            onBackClick = { },
            onCategoryClick = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Categories error state")
fun CategoriesScreenErrorPreview() {
    TestTaskVKTheme {
        CategoriesScreen(
            uiState = CategoriesUiState.OnError,
            onBackClick = { },
            onCategoryClick = { },
            onReloadButtonClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Categories no internet connection state")
fun CategoriesScreenNoInternetConnectionPreview() {
    TestTaskVKTheme {
        CategoriesScreen(
            uiState = CategoriesUiState.NoInternetConnection,
            onBackClick = { },
            onCategoryClick = { },
            onReloadButtonClick = { },
        )
    }
}
