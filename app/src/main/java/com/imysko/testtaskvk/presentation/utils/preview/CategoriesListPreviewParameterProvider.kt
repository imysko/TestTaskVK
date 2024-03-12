package com.imysko.testtaskvk.presentation.utils.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.imysko.testtaskvk.presentation.entities.CategoryUiModel

class CategoriesListPreviewParameterProvider :
    PreviewParameterProvider<List<CategoryUiModel>> {

    override val values = sequenceOf(
        listOf(
            CategoryUiModel(
                name = "first",
                title = "First",
            ),
            CategoryUiModel(
                name = "second",
                title = "Second",
            ),
        )
    )
}