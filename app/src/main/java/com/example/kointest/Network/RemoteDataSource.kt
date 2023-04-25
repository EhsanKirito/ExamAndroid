package com.example.kointest.Network

import com.example.kointest.NetworkParams
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val api: ApiService) {
    suspend fun getPopularMovies(page:Int): MovieResponse {
        return api.getMovieItems(NetworkParams.getPopularOptions(page = page))
    }

}