package com.example.atlmovie.ui.download

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovie.model.download.DownloadEntity
import com.example.atlmovie.service.MovieRepository
import kotlinx.coroutines.launch

class DownloadViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _downloadMovies = MutableLiveData<List<DownloadEntity>>()
    val downloadMovies: LiveData<List<DownloadEntity>> get() = _downloadMovies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchDownloadMovies() {
        viewModelScope.launch {
            try {
                val movies = repository.getAllDownloadMovies()
                _downloadMovies.value = movies
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            }
        }
    }

    fun deleteMovie(movie: DownloadEntity) {
        viewModelScope.launch {
            try {
                repository.deleteMovieFromDownload(movie)
                fetchDownloadMovies()
            } catch (e: Exception) {
                _error.value = e.message ?: "Delete error"
            }
        }
    }
}