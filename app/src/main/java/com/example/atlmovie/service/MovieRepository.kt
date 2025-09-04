package com.example.atlmovie.service

class MovieRepository(private val api: ApiServices) {

    suspend fun getPopular() = api.getPopular()
    suspend fun getTopRated() = api.getTopRated()
    suspend fun getUpcoming() = api.getUpcoming()
    suspend fun getNowPlaying() = api.getNewReleases()
    suspend fun getMovieDetail(movieId:Int) = api.getMovieDetail(movieId)

}