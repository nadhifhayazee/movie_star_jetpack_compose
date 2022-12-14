package com.nadhif.hayazee.moviestar.ui.screen.movie

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nadhif.hayazee.moviestar.R
import com.nadhif.hayazee.moviestar.module.RepositoryModule
import com.nadhif.hayazee.moviestar.ui.ViewModelFactory
import com.nadhif.hayazee.moviestar.ui.component.*
import com.nadhif.hayazee.moviestar.ui.theme.GreyText
import com.nadhif.hayazee.moviestar.ui.theme.MovieStarTheme

@Composable
fun MovieScreen(
    modifier: Modifier = Modifier,
    movieScreenViewModel: MovieScreenViewModel = viewModel(factory = ViewModelFactory(repository = RepositoryModule.dummyMovieRepository)),
    onNavigateToMovieDetail: (Int) -> Unit,
    onNavigateToAllMovie: (String) -> Unit,
    onNavigateToSearch: () -> Unit
) {

    val popularMovieUiState by movieScreenViewModel.popularMovieUiState.collectAsState()
    val upcomingMovieUiState by movieScreenViewModel.upcomingMovieUiState.collectAsState()
    val carouselUiState by movieScreenViewModel.carouselItems.collectAsState()
    val topRatedMovieUiState by movieScreenViewModel.topRatedMoviesUiState.collectAsState()
    val recommendationMovieUiState by movieScreenViewModel.recommendationMovieUiState.collectAsState()

    val popularTitle = stringResource(id = R.string.popular_movie_title)
    val upcomingTitle = stringResource(id = R.string.upcoming_movie_title)
    val topRatedTitle = stringResource(id = R.string.top_rated_movie_title)
    val recommendationTitle = stringResource(id = R.string.recommendation_movie_title)

    LaunchedEffect(true) {

        movieScreenViewModel.getPopularMovies()
        movieScreenViewModel.getUpcomingMovies()
        movieScreenViewModel.getMovieForCarousel()
        movieScreenViewModel.getTopRatedMovies()
        movieScreenViewModel.getRecommendationMovies()
    }



    Box() {

        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 30.dp),
        ) {

            MovieCarousel(onItemClicked = { onNavigateToMovieDetail(it) }, state = carouselUiState)


            MovieSection(
                modifier = modifier.padding(top = 16.dp),
                title = recommendationTitle,
                onShowMoreClicked = { onNavigateToAllMovie(recommendationTitle) },
                content = {
                    MovieRowContent(
                        movieState = recommendationMovieUiState,
                        onItemClicked = { movieId ->
                            onNavigateToMovieDetail(movieId)
                        }
                    )
                }
            )


            MovieSection(
                title = popularTitle,
                onShowMoreClicked = { onNavigateToAllMovie(popularTitle) },
                content = {
                    MovieRowContent(
                        movieState = popularMovieUiState,
                        onItemClicked = { movieId ->
                            onNavigateToMovieDetail(movieId)
                        }
                    )
                }
            )

            MovieSection(
                title = topRatedTitle,
                onShowMoreClicked = { onNavigateToAllMovie(topRatedTitle) },
                content = {
                    MovieRowContent(
                        movieState = topRatedMovieUiState,
                        onItemClicked = { movieId ->
                            onNavigateToMovieDetail(movieId)
                        }
                    )
                }
            )

            MovieSection(
                title = upcomingTitle,
                onShowMoreClicked = { onNavigateToAllMovie(upcomingTitle) },
                content = {
                    MovieRowContent(
                        movieState = upcomingMovieUiState,
                        onItemClicked = { movieId ->
                            onNavigateToMovieDetail(movieId)
                        }
                    )
                }
            )


        }


        MainAppBar(
            navigateSearchScreen = {
                onNavigateToSearch()
            }
        )


    }
}

@Composable
fun MovieRowContent(
    modifier: Modifier = Modifier,
    movieState: MovieRowUiState,
    onItemClicked: (Int) -> Unit
) {
    when (movieState) {
        is MovieRowUiState.Loading -> {
            MovieRowLoadingShimmer()
        }
        is MovieRowUiState.Success -> {
            MovieRow(
                movies = movieState.movies,
                onItemClicked = { movieId ->
                    onItemClicked.invoke(movieId)
                }
            )
        }

        is MovieRowUiState.Error -> {

            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = movieState.errorMessage,
                textAlign = TextAlign.Center,
                color = GreyText,
                fontSize = 12.sp

            )
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
fun MovieScreenPreview() {
    MovieStarTheme {
        MovieScreen(onNavigateToMovieDetail = {}, onNavigateToAllMovie = {}) {

        }
    }
}