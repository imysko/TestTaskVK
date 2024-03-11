package com.imysko.testtaskvk.ui.screens.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.imysko.testtaskvk.R
import com.imysko.testtaskvk.ui.components.base.TopBarWithReturn
import com.imysko.testtaskvk.ui.components.base.shimmerEffect
import com.imysko.testtaskvk.ui.entities.ProductUiModel
import com.imysko.testtaskvk.ui.theme.TestTaskVKTheme
import com.imysko.testtaskvk.ui.utils.preview.ProductUiModelPreviewParameterProvider
import kotlinx.coroutines.launch

@Composable
fun ProductScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),
) {
    val product by viewModel.product.collectAsState()

    ProductScreen(
        product = product,
        onBackClick = navController::popBackStack,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductScreen(
    product: ProductUiModel,
    onBackClick: () -> Unit,
) {
    val pagerState = rememberPagerState {
        product.imagesUrl.size
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopBarWithReturn(
                title = product.title,
                onBackClick = onBackClick,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    HorizontalPager(
                        modifier = Modifier.fillMaxWidth(),
                        state = pagerState,
                        key = { index -> product.imagesUrl[index] },
                    ) { index ->
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(300.dp),
                                model = product.imagesUrl[index],
                                alignment = Alignment.Center,
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                loading = {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .size(300.dp)
                                            .shimmerEffect(),
                                    )
                                },
                                error = {
                                    Image(
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .size(160.dp),
                                        painter = painterResource(id = R.drawable.image_gallery),
                                        contentDescription = stringResource(id = R.string.image_placeholder),
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                                    )
                                },
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(horizontal = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            },
                            content = {
                                Icon(
                                    modifier = Modifier.size(36.dp),
                                    imageVector = Icons.Outlined.ChevronLeft,
                                    contentDescription = stringResource(id = R.string.previous),
                                    tint = if (pagerState.currentPage == 0) {
                                        MaterialTheme.colorScheme.secondaryContainer
                                    } else {
                                        MaterialTheme.colorScheme.inversePrimary
                                    },
                                )
                            },
                        )

                        IconButton(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            },
                            content = {
                                Icon(
                                    modifier = Modifier.size(36.dp),
                                    imageVector = Icons.Outlined.ChevronRight,
                                    contentDescription = stringResource(id = R.string.next),
                                    tint = if (pagerState.currentPage == pagerState.pageCount - 1) {
                                        MaterialTheme.colorScheme.secondaryContainer
                                    } else {
                                        MaterialTheme.colorScheme.inversePrimary
                                    }
                                )
                            },
                        )
                    }
                }

                Text(text = product.title)
                Text(text = product.description)
            }
        },
    )
}

@Composable
@Preview(showBackground = true)
fun ProductScreenPreview(
    @PreviewParameter(ProductUiModelPreviewParameterProvider::class) parameter: ProductUiModel,
) {
    TestTaskVKTheme {
        ProductScreen(
            product = parameter,
            onBackClick = { },
        )
    }
}
