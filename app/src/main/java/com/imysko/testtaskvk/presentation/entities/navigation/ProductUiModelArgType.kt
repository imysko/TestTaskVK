package com.imysko.testtaskvk.presentation.entities.navigation

import com.google.gson.Gson
import com.imysko.testtaskvk.presentation.entities.ProductUiModel
import com.imysko.testtaskvk.presentation.utils.JsonNavType

class ProductUiModelArgType : JsonNavType<ProductUiModel>() {

    override fun fromJsonParse(value: String): ProductUiModel =
        Gson().fromJson(value, ProductUiModel::class.java)

    override fun ProductUiModel.getJsonParse(): String = Gson().toJson(this)
}