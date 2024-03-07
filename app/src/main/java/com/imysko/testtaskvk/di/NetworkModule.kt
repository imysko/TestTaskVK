package com.imysko.testtaskvk.di

import android.content.Context
import com.imysko.testtaskvk.utils.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Named("ApiBaseUrl")
    fun provideBaseUrl(): String = "https://dummyjson.com/"

    @Singleton
    @Provides
    @Named("ApiRetrofit")
    fun provideRetrofit(
        @Named("ApiBaseUrl") baseUrl: String,
        @Named("OkHttpClient") okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    @Named("OkHttpClient")
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.HEADERS
                }
            )
            .addInterceptor {
                val original = it.request()
                val newRequestBuilder = original.newBuilder()
                newRequestBuilder.addHeader("Content-Type", "application/json")
                newRequestBuilder.addHeader("Accept", "application/json")
                it.proceed(newRequestBuilder.build())
            }
            .addInterceptor {
                val original = it.request()
                val newUrl = original.url
                    .newBuilder()
                    .build()
                val newRequest = original
                    .newBuilder()
                    .url(newUrl)
                    .build()

                it.proceed(newRequest)
            }
            .callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }
}