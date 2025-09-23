package com.example.atlmovie.ui.mylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovie.model.mylist.MyListEntity
import com.example.atlmovie.service.MovieRepository
import kotlinx.coroutines.launch

class MyListViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _myListMovies = MutableLiveData<List<MyListEntity>>()
    val myListMovies: LiveData<List<MyListEntity>> get() = _myListMovies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchMyListMovies() {
        viewModelScope.launch {
            try {
                val movies = repository.getAllMyListMovies()
                _myListMovies.value = movies
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            }
        }
    }

    fun deleteMovie(movie: MyListEntity) {
        viewModelScope.launch {
            try {
                repository.deleteMovieFromMyList(movie)
                fetchMyListMovies()
            } catch (e: Exception) {
                _error.value = e.message ?: "Delete error"
            }
        }
    }
}