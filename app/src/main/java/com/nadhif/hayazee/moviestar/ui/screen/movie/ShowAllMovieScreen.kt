package com.nadhif.hayazee.moviestar.ui.screen.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nadhif.hayazee.moviestar.R
import com.nadhif.hayazee.moviestar.module.RepositoryModule
import com.nadhif.hayazee.moviestar.ui.ViewModelFactory
import com.nadhif.hayazee.moviestar.ui.component.MovieListContent


@Composable
fun ShowAllMovieScreen(
    modifier: Modifier = Modifier,
    title: String,
    navigateToMovieDetail: (Int) -> Unit,
    onBackPress: () -> Unit,
    movieScreenViewModel: MovieScreenViewModel = viewModel(
        factory = ViewModelFactory(
            RepositoryModule.dummyMovieRepository
        )
    )
) {


    Column {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    onBackPress.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button_cd),
                        tint = Color.White
                    )
                }
            },
        )

        getListMovieToObserve(
            movieScreenViewModel = movieScreenViewModel,
            title = title
        ).let { state ->
            MovieListContent(
                state = state,
                onItemClicked = {
                    navigateToMovieDetail(it)
                })
        }
    }
}

@Composable
private fun getListMovieToObserve(movieScreenViewModel: MovieScreenViewModel, title: String) =
    when (title) {
        stringResource(id = R.string.popular_movie_title) -> {
            LaunchedEffect(true) {
                movieScreenViewModel.getPopularMovies()
            }
            movieScreenViewModel.popularMovieUiState.collectAsState().value
        }

        stringResource(id = R.string.upcoming_movie_title) -> {
            LaunchedEffect(true) {
                movieScreenViewModel.getUpcomingMovies()
            }
            movieScreenViewModel.upcomingMovieUiState.collectAsState().value
        }

        stringResource(id = R.string.top_rated_movie_title) -> {
            LaunchedEffect(true) {
                movieScreenViewModel.getTopRatedMovies()
            }
            movieScreenViewModel.topRatedMoviesUiState.collectAsState().value
        }
        else -> {
            LaunchedEffect(true) {
                movieScreenViewModel.getRecommendationMovies()
            }
            movieScreenViewModel.recommendationMovieUiState.collectAsState().value

        }
    }
