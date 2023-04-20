package com.example.kointest.Network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET("/services/rest/")
    suspend fun getPopularPhotos(
        @Query("method") method: String = "flickr.photos.getPopular",
        @Query("user_id") userId: String = "34427466731@N01",
        @Query("extras") extras: String = "url_s",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("per_page") perPage: Int ,
        @Query("api_key") apiKey: String
    ): FlickrPhoto

    @GET("/services/rest/")
    suspend fun searchPopularPhotos(
        @Query("method") method: String = "flickr.photos.search",
        @Query("text") text: String,
        @Query("user_id") userId: String = "34427466731@N01",
        @Query("extras") extras: String = "url_s",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("per_page") perPage: Int ,
        @Query("api_key") apiKey: String
    ):FlickrPhoto
}
