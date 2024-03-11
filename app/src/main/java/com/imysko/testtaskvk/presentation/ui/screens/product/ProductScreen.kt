package com.imysko.testtaskvk.presentation.ui.screens.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.StarHalf
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.imysko.testtaskvk.R
import com.imysko.testtaskvk.presentation.entities.ProductUiModel
import com.imysko.testtaskvk.presentation.ui.components.base.TopBarWithReturn
import com.imysko.testtaskvk.presentation.ui.components.base.shimmerEffect
import com.imysko.testtaskvk.presentation.ui.theme.Orange
import com.imysko.testtaskvk.presentation.ui.theme.Red
import com.imysko.testtaskvk.presentation.ui.theme.TestTaskVKTheme
import com.imysko.testtaskvk.presentation.utils.preview.ProductUiModelPreviewParameterProvider
import kotlinx.coroutines.launch
import kotlin.math.ceil

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

@Composable
fun ProductScreen(
    product: ProductUiModel,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarWithReturn(
                title = product.title,
                onBackClick = onBackClick,
            )
        },
        content = {
            ProductBody(
                innerPadding = it,
                product = product,
            )
        },
    )
}

@Composable
private fun ProductBody(
    innerPadding: PaddingValues,
    product: ProductUiModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        item {
            ImagePager(imagesUrl = product.imagesUrl)
        }
        item {
            Text(
                modifier = Modifier.padding(start = 30.dp),
                text = product.title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
            )

        }
        item {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.description),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Medium,
                    ),
                )

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
        item {
            ProductDetailLabel(
                brand = product.brand,
                category = product.category,
            )
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                ProductRatingBlock(rating = product.rating)
                ProductPriceLabel(product = product)
                ProductStockLabel(
                    hasStock = product.hasStock,
                    stock = product.stock,
                )
            }
        }
    }
}

@Composable
private fun ProductDetailLabel(
    brand: String,
    category: String,
) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(
                text = "${stringResource(id = R.string.brand)}:",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                ),
            )

            Text(
                text = brand,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                ),
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(
                text = "${stringResource(id = R.string.category)}:",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                ),
            )

            Text(
                text = category,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                ),
            )
        }
    }
}

@Composable
private fun ProductPriceLabel(
    product: ProductUiModel,
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        if (product.hasDiscount) {
            Text(
                text = "${product.discountPrice} $",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
            Text(
                text = "${product.price} $",
                textDecoration = TextDecoration.LineThrough,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                ),
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "-${product.discountPercentage}%",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Red,
                ),
            )
        } else {
            Text(
                text = "${product.price} $",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
            )
        }
    }
}

@Composable
private fun ProductRatingBlock(
    rating: Float,
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row {
            (1..5).forEach { number ->
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = when {
                        number <= rating -> Icons.Rounded.Star
                        number == ceil(rating).toInt() -> Icons.AutoMirrored.Rounded.StarHalf
                        else -> Icons.Rounded.StarBorder
                    },
                    tint = Orange,
                    contentDescription = stringResource(id = R.string.rating),
                )
            }
        }

        Text(
            text = rating.toString(),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
            ),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ImagePager(
    imagesUrl: List<String>,
) {
    val pagerState = rememberPagerState {
        imagesUrl.size
    }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState,
            key = { index -> imagesUrl[index] },
        ) { index ->
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(300.dp),
                    model = imagesUrl[index],
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
}

@Composable
private fun ProductStockLabel(
    hasStock: Boolean,
    stock: Int,
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(horizontal = 10.dp, vertical = 5.dp),
    ) {
        if (hasStock) {
            Text(
                text = pluralStringResource(
                    id = R.plurals.items_in_stock,
                    count = stock,
                    stock,
                ),
            )
        } else {
            Text(
                text = stringResource(id = R.string.out_stock),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium,
                ),
            )
        }
    }
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
