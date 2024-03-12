package com.imysko.testtaskvk.presentation.entities.navigation

import com.google.gson.Gson
import com.imysko.testtaskvk.presentation.entities.CategoryUiModel
import com.imysko.testtaskvk.presentation.utils.JsonNavType

class CategoryUiModelArgType : JsonNavType<CategoryUiModel>() {

    override fun fromJsonParse(value: String): CategoryUiModel =
        Gson().fromJson(value, CategoryUiModel::class.java)

    override fun CategoryUiModel.getJsonParse(): String = Gson().toJson(this)
}