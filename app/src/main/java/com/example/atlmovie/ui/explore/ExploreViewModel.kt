package com.example.atlmovie.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovie.model.home.MovaListsHome
import com.example.atlmovie.model.home.MovaResult
import com.example.atlmovie.model.search.Result
import com.example.atlmovie.service.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ExploreViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<MovaResult>>()
    val movies: LiveData<List<MovaResult>> = _movies

    private val _searchMovies = MutableLiveData<List<Result>>()
    val searchMovies: LiveData<List<Result>> = _searchMovies

    private var _isError = MutableLiveData<String>()
    val isError : LiveData<String> = _isError

    fun getPopularData() = fetchMovies(
        call = { repository.getPopular() },
        target = _movies
    )

    fun searchMovies(query: String) = fetchSearchMovies(query)

    private fun fetchMovies(
        call: suspend () -> Response<MovaListsHome>,
        target: MutableLiveData<List<MovaResult>>
    ) {
        viewModelScope.launch {
            try {
                val response = call()
                if (response.isSuccessful) {
                    target.value = response.body()?.movaResults ?: emptyList()
                } else {
                    _isError.postValue("Error code: ${response.code()}")
                }
            } catch (e: Exception) {
                _isError.postValue(e.localizedMessage?.toString() ?: "Exception Popular!")
            }
        }
    }

    private fun fetchSearchMovies(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.getSearchMovies(query)
                if (response.isSuccessful) {
                    _searchMovies.value = response.body()?.results ?: emptyList()
                } else {
                    _isError.postValue("Search Error code: ${response.code()}")
                }
            } catch (e: Exception) {
                _isError.postValue(e.localizedMessage ?: "Exception Search!")
            }
        }
    }
}