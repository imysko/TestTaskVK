package com.imysko.testtaskvk.ui.entities.navigation

import com.google.gson.Gson
import com.imysko.testtaskvk.ui.entities.ProductUiModel
import com.imysko.testtaskvk.ui.utils.JsonNavType

class ProductUiModelArgType : JsonNavType<ProductUiModel>() {

    override fun fromJsonParse(value: String): ProductUiModel =
        Gson().fromJson(value, ProductUiModel::class.java)

    override fun ProductUiModel.getJsonParse(): String = Gson().toJson(this)
}