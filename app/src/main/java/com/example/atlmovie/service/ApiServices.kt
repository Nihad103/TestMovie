package com.example.atlmovie.service

import com.example.atlmovie.model.detail.MovaDetail
import com.example.atlmovie.model.home.MovaListsHome
import com.example.atlmovie.model.openyoutube.MovaVideos
import com.example.atlmovie.model.search.SearchModel
import com.example.atlmovie.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

//  https://api.themoviedb.org/3/movie/popular?api_key=0e3c7bf52adb5a1a383b2f8c42024ede
// Detail -> https://api.themoviedb.org/3/movie/911430?api_key=0e3c7bf52adb5a1a383b2f8c42024ede

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MovaListsHome>

    @GET("movie/popular")
    suspend fun getTopRated(
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MovaListsHome>

    @GET("movie/popular")
    suspend fun getUpcoming(
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MovaListsHome>

    @GET("movie/popular")
    suspend fun getNewReleases(
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MovaListsHome>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id")
        movieId: Int,
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MovaDetail>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id")
        movieId: Int,
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MovaVideos>

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("query")
        query: String,
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<SearchModel>
}