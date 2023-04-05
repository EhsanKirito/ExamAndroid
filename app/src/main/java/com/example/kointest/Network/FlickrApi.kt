package com.example.kointest.Network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET("/services/rest/")
    fun getPopularPhotos(
        @Query("method") method: String = "flickr.photos.getPopular",
        @Query("user_id") userId: String = "34427466731@N01",
        @Query("extras") extras: String = "url_s",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("per_page") perPage: Int = 100,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = "1c04e05bce6e626247758d120b372a73"
    ): Call<FlickrPhoto>
}