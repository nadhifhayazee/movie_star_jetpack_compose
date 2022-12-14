package com.nadhif.hayazee.moviestar.model

data class Movie(
    val id: Int?,
    val title: String?,
    val backdrop_path: String?,
    val genres: List<String>?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?
)

data class MovieResult(
    val results: List<Movie>?
)