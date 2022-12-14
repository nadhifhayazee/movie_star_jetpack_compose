package com.nadhif.hayazee.moviestar.ui.screen.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadhif.hayazee.moviestar.data.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieScreenViewModel(private val movieRepository: MovieRepository) : ViewModel() {


    private val loadingTime = 1000L

    private val _popularMovieUiState = MutableStateFlow<MovieRowUiState>(MovieRowUiState.Loading)
    val popularMovieUiState get() = _popularMovieUiState.asStateFlow()

    private val _upcomingMovieUiState = MutableStateFlow<MovieRowUiState>(MovieRowUiState.Loading)
    val upcomingMovieUiState get() = _upcomingMovieUiState.asStateFlow()

    private val _carouselItems = MutableStateFlow<MovieRowUiState>(MovieRowUiState.Loading)
    val carouselItems get() = _carouselItems.asStateFlow()

    private val _topRatedMovies = MutableStateFlow<MovieRowUiState>(MovieRowUiState.Loading)
    val topRatedMoviesUiState get() = _topRatedMovies.asStateFlow()

    private val _recommendationMovies = MutableStateFlow<MovieRowUiState>(MovieRowUiState.Loading)
    val recommendationMovieUiState get() = _recommendationMovies.asStateFlow()


    fun getPopularMovies() {
        if (_popularMovieUiState.value !is MovieRowUiState.Success) {

            viewModelScope.launch {
                delay(loadingTime)
                val movies = movieRepository.getPopularMovies()
                if (movies.isNullOrEmpty()) {
                    _popularMovieUiState.value =
                        MovieRowUiState.Error(errorMessage = "Data not found")
                } else {
                    _popularMovieUiState.value =
                        MovieRowUiState.Success(movies)
                }

            }
        }
    }

    fun getUpcomingMovies() {
        if (_upcomingMovieUiState.value !is MovieRowUiState.Success) {
            viewModelScope.launch {
                delay(loadingTime)
                val movies = movieRepository.getUpcomingMovies()
                if (movies.isNullOrEmpty()) {
                    _upcomingMovieUiState.value =
                        MovieRowUiState.Error(errorMessage = "Data not found")
                } else {
                    _upcomingMovieUiState.value =
                        MovieRowUiState.Success(movies)

                }

            }
        }

    }

    fun getMovieForCarousel() {
        if (_carouselItems.value !is MovieRowUiState.Success) {

            viewModelScope.launch {
                delay(loadingTime)
                val movies = movieRepository.getMovieForCarousel()
                if (movies.isNullOrEmpty()) {
                    _carouselItems.value = MovieRowUiState.Error(errorMessage = "Data not found")
                } else {
                    _carouselItems.value = MovieRowUiState.Success(movies)

                }
            }
        }
    }

    fun getTopRatedMovies() {
        if (_topRatedMovies.value !is MovieRowUiState.Success) {
            viewModelScope.launch {
                delay(loadingTime)
                val movies = movieRepository.getTopRatedMovies()
                if (movies.isNullOrEmpty()) {
                    _topRatedMovies.value = MovieRowUiState.Error(errorMessage = "Data not found")
                } else {
                    _topRatedMovies.value = MovieRowUiState.Success(movies)

                }
            }
        }
    }

    fun getRecommendationMovies() {
        if (_recommendationMovies.value !is MovieRowUiState.Success) {
            viewModelScope.launch {
                delay(loadingTime)
                val movies = movieRepository.getRecommendationMovies()
                if (movies.isNullOrEmpty()) {
                    _recommendationMovies.value =
                        MovieRowUiState.Error(errorMessage = "Data not found")
                } else {
                    _recommendationMovies.value = MovieRowUiState.Success(movies)

                }
            }
        }
    }
}