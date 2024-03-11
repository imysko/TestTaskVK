package com.imysko.testtaskvk.presentation.ui.components.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.imysko.testtaskvk.R
import com.imysko.testtaskvk.presentation.entities.ProductUiModel
import com.imysko.testtaskvk.presentation.ui.components.base.shimmerEffect
import com.imysko.testtaskvk.presentation.ui.theme.Orange
import com.imysko.testtaskvk.presentation.ui.theme.Red
import com.imysko.testtaskvk.presentation.ui.theme.TestTaskVKTheme
import com.imysko.testtaskvk.presentation.utils.preview.ProductUiModelPreviewParameterProvider

@Composable
fun ProductCard(
    product: ProductUiModel,
    onClick: (ProductUiModel) -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp,
        ),
        onClick = { onClick(product) },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProductThumbnail(thumbnailUrl = product.thumbnailUrl)
            ProductInformation(product = product)
        }
    }
}

@Composable
private fun ProductThumbnail(
    thumbnailUrl: String,
) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(8.dp)),
        model = thumbnailUrl,
        contentDescription = stringResource(id = R.string.product_image),
        contentScale = ContentScale.FillWidth,
        loading = {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .shimmerEffect(),
            )
        },
        error = {
            Box(
                modifier = Modifier
                    .size(100.dp),
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(60.dp),
                    painter = painterResource(id = R.drawable.image_gallery),
                    contentDescription = stringResource(id = R.string.image_placeholder),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                )
            }
        },
    )
}

@Composable
private fun ProductInformation(
    product: ProductUiModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(
                text = product.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
            )

            Text(
                text = product.brand,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray,
                ),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Rounded.Star,
                    tint = Orange,
                    contentDescription = stringResource(id = R.string.rating),
                )
                Text(
                    text = product.rating.toString(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                if (!product.hasStock) {
                    Text(
                        text = stringResource(id = R.string.out_stock),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                        ),
                    )
                } else if (product.hasDiscount) {
                    Text(
                        text = "${product.discountPrice} $",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Red,
                        ),
                    )
                    Text(
                        text = "${product.price} $",
                        textDecoration = TextDecoration.LineThrough,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Gray,
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
    }
}

@Composable
@Preview(name = "Product card")
fun ProductCardPreview(
    @PreviewParameter(ProductUiModelPreviewParameterProvider::class) parameter: ProductUiModel,
) {
    TestTaskVKTheme {
        ProductCard(
            product = parameter,
            onClick = { },
        )
    }
}
