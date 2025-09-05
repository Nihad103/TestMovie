package com.example.atlmovie.ui.detail.morelikethis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovie.model.home.MovaListsHome
import com.example.atlmovie.model.home.MovaResult
import com.example.atlmovie.service.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MoreLikeThisViewModel(
    private val movieRepository: MovieRepository

) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<MovaResult>>()
    val popularMovies: LiveData<List<MovaResult>> = _popularMovies

    private var _isError = MutableLiveData<String>()
    val isError : LiveData<String> = _isError


    fun getPopularData() = fetchMovies(
        call = { movieRepository.getPopular() },
        target = _popularMovies
    )

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
}