package com.nadhif.hayazee.moviestar.data

import com.nadhif.hayazee.moviestar.model.Movie

interface MovieRepository {
    fun getPopularMovies(): List<Movie>?
    fun getUpcomingMovies(): List<Movie>?
    fun getMovieById(movieId: Int): Movie?
    fun getMoviesByName(query: String): List<Movie>?
    fun addOrRemoveMovieFromWatchlist(movieId: Int): Boolean
    fun checkIsMovieWatchlist(movieId: Int): Boolean
    fun getWatchlistMovies(): List<Movie>
    fun getAllMovies(): List<Movie>?
    fun getMovieForCarousel(): List<Movie>?
    fun getTopRatedMovies(): List<Movie>?
    fun getRecommendationMovies(): List<Movie>?
}