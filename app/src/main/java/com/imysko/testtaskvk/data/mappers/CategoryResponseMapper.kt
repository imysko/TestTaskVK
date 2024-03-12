package com.imysko.testtaskvk.data.mappers

import com.imysko.testtaskvk.domain.entities.Category

fun String.mapToDomainCategory(): Category = this.let { from ->
    return Category(
        name = from,
    )
}