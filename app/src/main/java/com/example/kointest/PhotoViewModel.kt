package com.example.kointest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kointest.Network.FlickrApi
import com.example.kointest.Network.FlickrPhoto
import com.example.kointest.Network.FlickrSearchApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor() : ViewModel() {
    val _photoList = MutableLiveData<FlickrPhoto>()
    val photoList: LiveData<FlickrPhoto> = _photoList
    val _searchList = MutableLiveData<FlickrPhoto>()
    val searchList: LiveData<FlickrPhoto> = _searchList

    fun getPhotoList() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(FlickrApi::class.java)

        retrofit.getPopularPhotos(apiKey = "1c04e05bce6e626247758d120b372a73").enqueue(object :
            Callback<FlickrPhoto> {
            override fun onResponse(call: Call<FlickrPhoto>, response: Response<FlickrPhoto>) {
                _photoList.postValue(response.body())
            }

            override fun onFailure(call: Call<FlickrPhoto>, t: Throwable) {
                t.message
            }

        })

    }

    fun searchPhotoList(text:String){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(FlickrSearchApi::class.java)
        retrofit.searchPopularPhotos(text = text, apiKey = "1c04e05bce6e626247758d120b372a73")
            .enqueue(object : Callback<FlickrPhoto?> {
            override fun onResponse(call: Call<FlickrPhoto?>, response: Response<FlickrPhoto?>) {
                _searchList.postValue(response.body())
            }

            override fun onFailure(call: Call<FlickrPhoto?>, t: Throwable) {
                t.message
            }
        })
    }

}