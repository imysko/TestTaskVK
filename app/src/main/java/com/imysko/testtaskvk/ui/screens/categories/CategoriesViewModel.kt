package com.imysko.testtaskvk.ui.screens.categories

import com.imysko.testtaskvk.domain.usecase.GetCategoriesListUseCase
import com.imysko.testtaskvk.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesListUseCase: GetCategoriesListUseCase,
) : BaseViewModel() {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() {
        call({
            getCategoriesListUseCase()
        }, onSuccess = {
            _categories.tryEmit(it)
        })
    }
}