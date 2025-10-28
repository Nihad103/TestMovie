package com.example.atlmovie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovie.model.home.MovaListsHome
import com.example.atlmovie.model.home.MovaResult
import com.example.atlmovie.service.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val firstPopularMovie = MutableLiveData<MovaResult?>()

    private val _popularMovies = MutableLiveData<List<MovaResult>>()
    val popularMovies: LiveData<List<MovaResult>> = _popularMovies

    private val _topRatedMovies = MutableLiveData<List<MovaResult>>()
    val topRatedMovies: LiveData<List<MovaResult>> = _topRatedMovies

    private val _upcomingMovies = MutableLiveData<List<MovaResult>>()
    val upcomingMovies: LiveData<List<MovaResult>> = _upcomingMovies

    private val _newReleasesMovies = MutableLiveData<List<MovaResult>>()
    val newReleasesMovies: LiveData<List<MovaResult>> = _newReleasesMovies

    private var _isError = MutableLiveData<String>()
    val isError : LiveData<String> = _isError

    fun getPopularData() = fetchMovies(
        call = { movieRepository.getPopular() },
        target = _popularMovies
    ) {
        firstPopularMovie.value = it.firstOrNull()
    }

    fun getTopRatedData() = fetchMovies(
        call = { movieRepository.getTopRated() },
        target = _topRatedMovies
    )

    fun getUpcomingData() = fetchMovies(
        call = { movieRepository.getUpcoming() },
        target = _upcomingMovies
    )

    fun getNewReleasesData() = fetchMovies(
        call = { movieRepository.getNowPlaying() },
        target = _newReleasesMovies
    )

    private fun fetchMovies(
        call: suspend () -> Response<MovaListsHome>,
        target: MutableLiveData<List<MovaResult>>,
        onSuccess: (List<MovaResult>) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                val response = call()
                if (response.isSuccessful) {
                    val list = response.body()?.movaResults ?: emptyList()
                    target.value = list
                    onSuccess(list)
                } else {
                    _isError.postValue("Error code: ${response.code()}")
                }
            } catch (e: Exception) {
                _isError.postValue(e.localizedMessage?.toString() ?: "Exception Popular!")
            }
        }
    }
}