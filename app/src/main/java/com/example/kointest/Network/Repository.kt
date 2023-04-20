package com.example.kointest.Network

import com.example.kointest.Network.FlickrApi
import com.example.kointest.Network.FlickrPhoto
import javax.inject.Inject

class Repository @Inject constructor(private val flickrApi: FlickrApi) {
    suspend fun getPhotoList(perPage:Int):FlickrPhoto{
        return flickrApi.getPopularPhotos(apiKey = "1c04e05bce6e626247758d120b372a73", perPage = perPage)
    }
    suspend fun searchPhotoList(text:String,perPage:Int):FlickrPhoto{
        return flickrApi.searchPopularPhotos(text = text, apiKey = "1c04e05bce6e626247758d120b372a73", perPage = perPage)
    }
}