package com.example.atlmovie.ui.detail.trailers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlmovie.model.openyoutube.MovaVideos
import com.example.atlmovie.model.openyoutube.Result
import com.example.atlmovie.service.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class TrailersViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _trailers = MutableLiveData<List<Result>>()
    val trailers: LiveData<List<Result>> = _trailers

    private val _isError = MutableLiveData<String>()
    val isError: LiveData<String> = _isError

    fun fetchTrailers(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = movieRepository.getMovieVideos(movieId)
                Log.d("TrailersFragment", "Trailers response: ${response.body()?.results}")
                if (response.isSuccessful) {
                    _trailers.value = response.body()?.results ?: emptyList()
                } else {
                    _isError.value = "Error code: ${response.code()}"
                }
            } catch (e: Exception) {
                _isError.value = e.localizedMessage ?: "Unknown error"
            }
        }
    }
}
