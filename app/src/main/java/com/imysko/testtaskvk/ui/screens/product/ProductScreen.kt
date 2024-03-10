package com.imysko.testtaskvk.ui.screens.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.imysko.testtaskvk.ui.components.base.TopBarWithReturn
import com.imysko.testtaskvk.ui.components.base.shimmerEffect
import com.imysko.testtaskvk.ui.entities.ProductUiModel
import com.imysko.testtaskvk.ui.theme.TestTaskVKTheme
import com.imysko.testtaskvk.ui.utils.preview.ProductUiModelPreviewParameterProvider

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
//    val scope = rememberCoroutineScope()

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
                Box {
                    HorizontalPager(
                        modifier = Modifier
                            .fillMaxWidth(),
                        state = pagerState,
                        key = { index -> product.imagesUrl[index] },
                    ) { index ->
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .height(300.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            alignment = Alignment.Center,
                            model = product.imagesUrl[index],
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            loading = {
                                Box(
                                    modifier = Modifier
                                        .size(height = 300.dp, width = 400.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .shimmerEffect(),
                                )
                            }
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
