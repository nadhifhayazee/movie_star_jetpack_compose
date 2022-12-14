package com.nadhif.hayazee.moviestar.ui.screen.movie

import com.nadhif.hayazee.moviestar.model.Movie

sealed class MovieRowUiState {
    object Loading : MovieRowUiState()
    data class Success(val movies: List<Movie>) : MovieRowUiState()
    data class Error(val errorMessage: String) : MovieRowUiState()
}
