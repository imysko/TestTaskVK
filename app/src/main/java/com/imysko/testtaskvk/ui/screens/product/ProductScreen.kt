package com.imysko.testtaskvk.ui.screens.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ManageSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imysko.testtaskvk.domain.entities.Product
import com.imysko.testtaskvk.ui.base.TopBarWithReturn
import com.imysko.testtaskvk.ui.theme.TestTaskVKTheme

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
    product: Product,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarWithReturn(
                title = product.title,
                action = {
                    IconButton(
                        onClick = onBackClick,
                        content = {
                            Icon(
                                modifier = Modifier.size(36.dp),
                                imageVector = Icons.AutoMirrored.Outlined.ManageSearch,
                                contentDescription = "Назад",
                            )
                        },
                    )
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            ) {
                Text(text = product.title)
                Text(text = product.description)
            }
        },
    )
}

@Composable
@Preview(showBackground = true)
fun ProductScreenPreview() {
    TestTaskVKTheme {
        ProductScreen(
            product = Product(
                title = "",
                description = "",
                price = 0,
                discountPercentage = 0f,
                rating = 0f,
                stock = 0,
                brand = "",
                category = "",
            ),
            onBackClick = { },
        )
    }
}