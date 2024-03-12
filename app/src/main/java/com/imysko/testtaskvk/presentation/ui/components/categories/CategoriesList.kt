package com.imysko.testtaskvk.presentation.ui.components.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateNext
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imysko.testtaskvk.presentation.entities.CategoryUiModel

@Composable
fun CategoriesList(
    categories: List<CategoryUiModel>,
    onCategoryClick: (CategoryUiModel) -> Unit,
) {
    LazyColumn {
        itemsIndexed(categories) { index, category ->
            CategoryItem(
                category = category,
                onClick = onCategoryClick,
            )

            if (index < categories.lastIndex) {
                HorizontalDivider(thickness = 2.dp)
            }
        }
    }
}


@Composable
private fun CategoryItem(
    category: CategoryUiModel,
    onClick: (CategoryUiModel) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(category) }
            .padding(vertical = 10.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = category.title)

        Icon(
            modifier = Modifier.size(36.dp),
            imageVector = Icons.AutoMirrored.Outlined.NavigateNext,
            contentDescription = null,
        )
    }
}
