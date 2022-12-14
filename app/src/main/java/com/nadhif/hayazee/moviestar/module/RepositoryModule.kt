package com.nadhif.hayazee.moviestar.module

import android.content.Context
import com.nadhif.hayazee.moviestar.data.DummyMovieRepository
import com.nadhif.hayazee.moviestar.data.MovieRepository
import com.nadhif.hayazee.moviestar.model.Movie

object RepositoryModule {
    private var dummyMovie: MovieRepository? = null
    val dummyMovieRepository get() = dummyMovie!!

    var watchlistMovies = mutableListOf<Movie>()

    fun initModule(context: Context) {
        dummyMovie = DummyMovieRepository(context)
    }
}