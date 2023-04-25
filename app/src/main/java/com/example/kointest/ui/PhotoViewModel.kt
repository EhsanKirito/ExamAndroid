package com.example.kointest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kointest.Network.MovieResponse
import com.example.kointest.Network.MovieResult
import com.example.kointest.Network.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val repository: RemoteDataSource) : ViewModel() {
    private val _movieList = MutableLiveData<MovieResponse>()
    val movieList: LiveData<MovieResponse> = _movieList
    private val _searchList = MutableLiveData<MovieResponse>()
    val searchList: LiveData<MovieResponse> = _searchList
    var page:Int = 1

    fun getMovieList() {
            viewModelScope.launch {
                try {
                _movieList.postValue(repository.getPopularMovies(page))
                }catch (e:Exception){
                    e.message
                }
            }
    }



    fun nexPage() {
        page += 1
        viewModelScope.launch {
            try {
                _movieList.postValue(repository.getPopularMovies(page))
            }catch (e:Exception){
                e.message
            }
        }
    }
}