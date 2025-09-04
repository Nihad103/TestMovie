package com.example.atlmovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovie.model.detail.MovaDetail
import com.example.atlmovie.service.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

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
                    }
                } else {
                    _error.postValue("Error code: ${response.code()}")
                }
            } catch (e: Exception) {
                _error.postValue(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}