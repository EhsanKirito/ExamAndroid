package com.example.kointest.Network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.flickr.com")
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    fun provideService(retrofit: Retrofit):FlickrApi = retrofit.create(FlickrApi::class.java)
}