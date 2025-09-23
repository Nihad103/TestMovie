package com.example.atlmovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovie.model.detail.MovaDetail
import com.example.atlmovie.model.download.DownloadEntity
import com.example.atlmovie.service.MovieRepository
import com.example.atlmovie.utils.toMyListEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _isInMyList = MutableLiveData<Boolean>()
    val isInMyList: LiveData<Boolean> get() = _isInMyList

    private val _isInDownload = MutableLiveData<Boolean>()
    val isInDownload: LiveData<Boolean> get() = _isInDownload

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    private val _movieDetail = MutableLiveData<MovaDetail>()
    val movieDetail: LiveData<MovaDetail> get() = _movieDetail

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = movieRepository.getMovieDetail(movieId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _movieDetail.value = it
                        checkIfInMyList(it.id)
                    }
                } else {
                    _error.postValue("Error code: ${response.code()}")
                }
            } catch (e: Exception) {
                _error.postValue(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun checkIfInMyList(movieId: Int) {
        viewModelScope.launch {
            val exists = movieRepository.isMovieInMyList(movieId)
            _isInMyList.value = exists
        }
    }

    fun toggleBookmark() {
        val movie = _movieDetail.value ?: return
        viewModelScope.launch {
            try {
                if (_isInMyList.value == true) {
                    movieRepository.deleteMovieFromMyList(movie.toMyListEntity())
                    _isInMyList.value = false
                    _toastMessage.value = "Removed from My List"
                } else {
                    movieRepository.addMovieToMyList(movie)
                    _isInMyList.value = true
                    _toastMessage.value = "Added to My List"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Database error"
            }
        }
    }

    private fun checkIfInDownload(movieId: Int) {
        viewModelScope.launch {
            _isInDownload.value = movieRepository.isMovieInDownload(movieId)
        }
    }

    fun startDownload(onProgress: (Int) -> Unit, onComplete: () -> Unit, onError: (String) -> Unit) {
        val movie = _movieDetail.value ?: return
        viewModelScope.launch {
            try {
                val exists = movieRepository.isMovieInDownload(movie.id)
                if (exists) {
                    _toastMessage.value = "Already in Downloads"
                    return@launch
                }

                for (i in 1..100) {
                    delay(50)
                    onProgress(i)
                }

                movieRepository.addMovieToDownload(movie)
                _isInDownload.value = true
                onComplete()
            } catch (e: Exception) {
                onError(e.message ?: "Download error")
            }
        }
    }

    fun addMovieToDownload(download: MovaDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                movieRepository.addMovieToDownload(download)
            } catch (e: Exception) {
                _error.postValue(e.message ?: "Download DB error")
            }
        }
    }
}