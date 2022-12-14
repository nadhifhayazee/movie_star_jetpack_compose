package com.nadhif.hayazee.moviestar.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nadhif.hayazee.moviestar.data.MovieRepository
import com.nadhif.hayazee.moviestar.ui.screen.movie.MovieScreenViewModel
import com.nadhif.hayazee.moviestar.ui.screen.movie_detail.MovieDetailViewModel
import com.nadhif.hayazee.moviestar.ui.screen.search.SearchScreenViewModel
import com.nadhif.hayazee.moviestar.ui.screen.watchlist.WatchlistScreenViewModel

class ViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieScreenViewModel::class.java)) {
            return MovieScreenViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(WatchlistScreenViewModel::class.java)) {
            return WatchlistScreenViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(SearchScreenViewModel::class.java)) {
            return SearchScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}