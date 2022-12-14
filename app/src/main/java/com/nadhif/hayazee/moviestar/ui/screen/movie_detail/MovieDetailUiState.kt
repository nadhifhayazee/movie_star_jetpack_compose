package com.nadhif.hayazee.moviestar.ui.screen.movie_detail

import com.nadhif.hayazee.moviestar.model.Movie

data class MovieDetailUiState(
    val isLoading: Boolean,
    val movie: Movie? = null,
    val isWatchListed: Boolean = false
)
