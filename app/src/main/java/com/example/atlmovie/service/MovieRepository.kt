package com.example.atlmovie.service

import com.example.atlmovie.dao.CardDao
import com.example.atlmovie.dao.DownloadDao
import com.example.atlmovie.dao.MyListDao
import com.example.atlmovie.model.CardModel
import com.example.atlmovie.model.detail.MovaDetail
import com.example.atlmovie.model.download.DownloadEntity
import com.example.atlmovie.model.mylist.MyListEntity
import com.example.atlmovie.utils.toDownloadEntity
import com.example.atlmovie.utils.toMyListEntity
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val api: ApiServices,
    private val myListDao: MyListDao,
    private val downloadDao: DownloadDao,
    private val cardDao: CardDao,
    ) {
    suspend fun getPopular() = api.getPopular()
    suspend fun getTopRated() = api.getTopRated()
    suspend fun getUpcoming() = api.getUpcoming()
    suspend fun getNowPlaying() = api.getNewReleases()
    suspend fun getMovieDetail(movieId:Int) = api.getMovieDetail(movieId)
    suspend fun getMovieVideos(movieId: Int) = api.getMovieVideos(movieId)
    suspend fun getSearchMovies(query: String) = api.getSearchMovies(query)

    // bunlar mylist ucundu
    suspend fun addMovieToMyList(movie: MovaDetail) {
        myListDao.insertMovie(movie.toMyListEntity())
    }

    suspend fun deleteMovieFromMyList(movie: MyListEntity) {
        myListDao.deleteMovie(movie)
    }

    suspend fun getAllMyListMovies(): List<MyListEntity> {
        return myListDao.getAllMovies()
    }

    suspend fun isMovieInMyList(movieId: Int): Boolean {
        return myListDao.isMovieExist(movieId)
    }

    // bunlar download ucundur
    suspend fun addMovieToDownload(movie: MovaDetail) {
        downloadDao.insertDownload(movie.toDownloadEntity())
    }

    suspend fun deleteMovieFromDownload(movie: DownloadEntity) {
        downloadDao.deleteDownload(movie)
    }

    suspend fun getAllDownloadMovies(): List<DownloadEntity> {
        return downloadDao.getAllDownloads()
    }

    suspend fun isMovieInDownload(movieId: Int): Boolean {
        return downloadDao.isDownloadExist(movieId)
    }

    // Select Card
    fun getCards(): Flow<List<CardModel>> {
        return cardDao.getAllCards()

    }
    suspend fun addCard(card: CardModel){
        cardDao.addCard(card)
    }
    suspend fun deleteCard(id: Int): Int {
        return cardDao.deleteCard(id)
    }
}