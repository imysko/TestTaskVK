package com.imysko.testtaskvk.presentation.entities

import android.net.Uri
import com.google.gson.Gson

data class CategoryUiModel(
    val name: String,
    val title: String,
) {
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}
