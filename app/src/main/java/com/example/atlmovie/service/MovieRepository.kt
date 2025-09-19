package com.example.atlmovie.service

class MovieRepository(private val api: ApiServices) {

    suspend fun getPopular() = api.getPopular()
    suspend fun getTopRated() = api.getTopRated()
    suspend fun getUpcoming() = api.getUpcoming()
    suspend fun getNowPlaying() = api.getNewReleases()
    suspend fun getMovieDetail(movieId:Int) = api.getMovieDetail(movieId)
    suspend fun getMovieVideos(movieId: Int) = api.getMovieVideos(movieId)
    suspend fun getSearchMovies(query: String) = api.getSearchMovies(query)
}