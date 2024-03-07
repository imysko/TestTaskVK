package com.imysko.testtaskvk.di

import com.imysko.testtaskvk.domain.usecase.GetCategoriesListUseCase
import com.imysko.testtaskvk.domain.usecase.GetCategoriesListUseCaseImpl
import com.imysko.testtaskvk.domain.usecase.GetProductListByCategoryUseCase
import com.imysko.testtaskvk.domain.usecase.GetProductListByCategoryUseCaseImpl
import com.imysko.testtaskvk.domain.usecase.GetProductListUseCase
import com.imysko.testtaskvk.domain.usecase.GetProductListUseCaseImpl
import com.imysko.testtaskvk.domain.usecase.SearchProductUseCase
import com.imysko.testtaskvk.domain.usecase.SearchProductUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    internal abstract fun bindGetProductListUseCase(
        getProductListUseCaseImpl: GetProductListUseCaseImpl,
    ): GetProductListUseCase

    @Binds
    @Singleton
    internal abstract fun bindSearchProductUseCase(
        searchProductUseCaseImpl: SearchProductUseCaseImpl,
    ): SearchProductUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetProductListByCategoryUseCase(
        getProductListByCategoryUseCaseImpl: GetProductListByCategoryUseCaseImpl,
    ): GetProductListByCategoryUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetCategoriesListUseCase(
        getCategoriesListUseCaseImpl: GetCategoriesListUseCaseImpl,
    ): GetCategoriesListUseCase
}