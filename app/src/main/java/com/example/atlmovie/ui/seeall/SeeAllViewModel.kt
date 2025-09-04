package com.example.atlmovie.ui.seeall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovie.model.home.MovaResult
import com.example.atlmovie.service.MovieRepository
import com.example.atlmovie.utils.SeeAllType
import kotlinx.coroutines.launch

class SeeAllViewModel(
    private val repository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movies = MutableLiveData<List<MovaResult>>()
    val movies: LiveData<List<MovaResult>> = _movies

    private val _isError = MutableLiveData<String>()
    val isError: LiveData<String> = _isError

    private val listType = SeeAllType.valueOf(
        savedStateHandle["listType"] ?: SeeAllType.POPULAR.name
    )

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val response = when (listType) {
                    SeeAllType.POPULAR -> repository.getPopular()
                    SeeAllType.TOP_RATED -> repository.getTopRated()
                    SeeAllType.UPCOMING -> repository.getUpcoming()
                    SeeAllType.NEW_RELEASE -> repository.getNowPlaying()
                }

                if (response.isSuccessful) {
                    _movies.value = response.body()?.movaResults ?: emptyList()
                } else {
                    _isError.postValue("Error code: ${response.code()}")
                }

            } catch (e: Exception) {
                _isError.postValue(e.localizedMessage ?: "Error while fetching movies")
            }
        }
    }
}
