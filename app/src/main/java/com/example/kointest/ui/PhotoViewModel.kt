package com.example.kointest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kointest.Network.FlickrPhoto
import com.example.kointest.Network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _photoList = MutableLiveData<FlickrPhoto>()
    val photoList: LiveData<FlickrPhoto> = _photoList
    private val _searchList = MutableLiveData<FlickrPhoto>()
    val searchList: LiveData<FlickrPhoto> = _searchList
    var perPage:Int = 20

    fun getPhotoList() {
            viewModelScope.launch {
                try {
                _photoList.postValue(repository.getPhotoList(perPage))
                }catch (e:Exception){
                    e.message
                }
            }
    }

    fun searchPhotoList(text:String){
            viewModelScope.launch {
                try {
                _searchList.postValue(repository.searchPhotoList(text,perPage))
                }catch (e:Exception){
                    e.message
                }
            }
    }

    fun nexPage() {
        perPage += 20
        viewModelScope.launch {
            try {
                _photoList.postValue(repository.getPhotoList(perPage))
            }catch (e:Exception){
                e.message
            }
        }
    }
}