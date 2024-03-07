package com.imysko.testtaskvk.di

import com.imysko.testtaskvk.data.repositories.CategoriesRepositoryImpl
import com.imysko.testtaskvk.data.repositories.ProductsRepositoryImpl
import com.imysko.testtaskvk.domain.repositories.CategoriesRepository
import com.imysko.testtaskvk.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindProductsRepository(
        productsRepositoryImpl: ProductsRepositoryImpl,
    ): ProductsRepository

    @Binds
    @Singleton
    internal abstract fun bindCategoriesRepository(
        categoriesRepositoryImpl: CategoriesRepositoryImpl,
    ): CategoriesRepository
}