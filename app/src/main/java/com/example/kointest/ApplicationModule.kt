package com.example.kointest

import com.example.kointest.Network.FlickrApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Singleton
    @Provides
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.flickr.com")
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit):FlickrApi = retrofit.create(FlickrApi::class.java)
}