package com.nadhif.hayazee.moviestar.ui.screen.movie_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.nadhif.hayazee.moviestar.data.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieDetailViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {


    private val _movieDetailUiState: MutableStateFlow<MovieDetailUiState> = MutableStateFlow(
        MovieDetailUiState(isLoading = true)
    )
    val movieDetailUiState get() = _movieDetailUiState.asStateFlow()

    fun getMovieById(movieId: Int) {
        _movieDetailUiState.update {
            it.copy(
                isLoading = false,
                movie = movieRepository.getMovieById(movieId),
                isWatchListed = movieRepository.checkIsMovieWatchlist(movieId)
            )
        }
    }

    fun addOrRemoveMovieFromWatchlist(movieId: Int) {
        _movieDetailUiState.update {
            it.copy(isWatchListed = movieRepository.addOrRemoveMovieFromWatchlist(movieId))
        }
    }
}