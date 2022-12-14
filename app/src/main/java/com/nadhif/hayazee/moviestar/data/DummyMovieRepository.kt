package com.nadhif.hayazee.moviestar.data

import android.content.Context
import com.google.gson.Gson
import com.nadhif.hayazee.moviestar.model.Movie
import com.nadhif.hayazee.moviestar.model.MovieResult
import com.nadhif.hayazee.moviestar.module.RepositoryModule
import com.nadhif.hayazee.moviestar.util.getJsonDataFromAsset

class DummyMovieRepository(
    private val context: Context,
    private val watchlist: MutableList<Movie> = RepositoryModule.watchlistMovies
) : MovieRepository {

    override fun getPopularMovies(): List<Movie>? {
        return Gson().fromJson(
            getJsonDataFromAsset(context = context, "dummy_popular_movie.json"),
            MovieResult::class.java
        ).results
    }

    override fun getUpcomingMovies(): List<Movie>? {
        return Gson().fromJson(
            getJsonDataFromAsset(context = context, "dummy_upcoming_movie.json"),
            MovieResult::class.java
        ).results
    }

    override fun getMovieById(movieId: Int): Movie? {
        return getAllMovies()?.find { it.id == movieId }
    }

    override fun getMoviesByName(query: String): List<Movie>? {
        return getAllMovies()?.filter { it.title?.contains(query, ignoreCase = true) ?: false }
    }

    override fun addOrRemoveMovieFromWatchlist(movieId: Int): Boolean {
        val movie = watchlist.find { it.id == movieId }
        return if (movie != null) {
            watchlist.remove(movie)
            false
        } else {
            getMovieById(movieId)?.let {
                watchlist.add(it)
            }
            true
        }
    }

    override fun checkIsMovieWatchlist(movieId: Int): Boolean {
        return watchlist.find { it.id == movieId } != null
    }

    override fun getWatchlistMovies(): List<Movie> {
        return watchlist
    }


    override fun getAllMovies(): List<Movie>? {
        return Gson().fromJson(
            getJsonDataFromAsset(context = context, "dummy_all_movie.json"),
            MovieResult::class.java
        ).results
    }

    override fun getMovieForCarousel(): List<Movie>? {
        return getUpcomingMovies()?.shuffled()?.slice(0..5)
    }

    override fun getTopRatedMovies(): List<Movie>? {
        return getPopularMovies()?.shuffled()
    }

    override fun getRecommendationMovies(): List<Movie>? {
        return getAllMovies()?.shuffled()?.slice(0..10)
    }


}