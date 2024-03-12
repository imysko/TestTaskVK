package com.imysko.testtaskvk.presentation.mappers

import com.imysko.testtaskvk.domain.entities.Category
import com.imysko.testtaskvk.presentation.entities.CategoryUiModel

fun Category.mapToUiModel(): CategoryUiModel = this.let { from ->
    return CategoryUiModel(
        name = from.name,
        title = from.name
            .replaceFirstChar {
                if (it.isLowerCase()) {
                    it.titlecase()
                } else {
                    it.toString()
                }
            }
            .replace('-', ' '),
    )
}