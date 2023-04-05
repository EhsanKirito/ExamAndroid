package com.example.kointest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kointest.Network.FlickrApi
import com.example.kointest.Network.FlickrPhoto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotoViewModel : ViewModel() {
    val _photoList = MutableLiveData<FlickrPhoto>()
    val photoList: LiveData<FlickrPhoto> = _photoList


    fun getPhotoList() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val request = retrofit.create(FlickrApi::class.java)

        request.getPopularPhotos().enqueue(object :
            Callback<FlickrPhoto> {
            override fun onResponse(call: Call<FlickrPhoto>, response: Response<FlickrPhoto>) {
                _photoList.postValue(response.body())
            }

            override fun onFailure(call: Call<FlickrPhoto>, t: Throwable) {
                t.message
            }

        })

    }
}