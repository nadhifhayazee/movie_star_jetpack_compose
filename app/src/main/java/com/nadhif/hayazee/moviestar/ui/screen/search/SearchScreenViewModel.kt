package com.nadhif.hayazee.moviestar.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nadhif.hayazee.moviestar.data.MovieRepository
import com.nadhif.hayazee.moviestar.model.Movie
import com.nadhif.hayazee.moviestar.ui.screen.movie.MovieRowUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SearchScreenViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _searchResults = MutableStateFlow<MovieRowUiState>(MovieRowUiState.Success(movies = movieRepository.getAllMovies() ?:  arrayListOf()))
    val searchResults get() = _searchResults.asStateFlow()

    val query = mutableStateOf("")

    fun searchMovieByName(query: String) {
        _searchResults.update {
            if (query.length > 2) movieRepository.getMoviesByName(query).let {
                if (it.isNullOrEmpty()) MovieRowUiState.Error("Movie not found!")
                else MovieRowUiState.Success(it)
            } else movieRepository.getAllMovies().let {
                if (it.isNullOrEmpty()) MovieRowUiState.Error("Movie not found!")
                else MovieRowUiState.Success(it)
            }
        }
    }
}