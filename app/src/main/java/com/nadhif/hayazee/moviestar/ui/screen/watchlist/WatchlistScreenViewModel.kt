package com.nadhif.hayazee.moviestar.ui.screen.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadhif.hayazee.moviestar.data.MovieRepository
import com.nadhif.hayazee.moviestar.ui.screen.movie.MovieRowUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WatchlistScreenViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _watchlistMovies =
        MutableStateFlow<MovieRowUiState>(MovieRowUiState.Loading)
    val watchlistMovies get() = _watchlistMovies.asStateFlow()


    fun getWatchlist() {
        viewModelScope.launch {
            movieRepository.getWatchlistMovies().let { movies ->
                if (movies.isEmpty()) {
                    _watchlistMovies.update {
                        MovieRowUiState.Error("Movie not found!")
                    }
                } else {
                    _watchlistMovies.update {
                        MovieRowUiState.Success(movies)
                    }
                }
            }
        }

    }
}