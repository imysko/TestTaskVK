package com.imysko.testtaskvk.di

import com.imysko.testtaskvk.data.services.CategoriesService
import com.imysko.testtaskvk.data.services.ProductsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    internal fun providerProductsService(
        @Named("ApiRetrofit") retrofit: Retrofit,
    ): ProductsService = retrofit.create(ProductsService::class.java)

    @Provides
    internal fun providerCategoriesService(
        @Named("ApiRetrofit") retrofit: Retrofit,
    ): CategoriesService = retrofit.create(CategoriesService::class.java)
}