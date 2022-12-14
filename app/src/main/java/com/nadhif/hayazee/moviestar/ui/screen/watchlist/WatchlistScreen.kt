package com.nadhif.hayazee.moviestar.ui.screen.watchlist

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nadhif.hayazee.moviestar.R
import com.nadhif.hayazee.moviestar.module.RepositoryModule
import com.nadhif.hayazee.moviestar.ui.ViewModelFactory
import com.nadhif.hayazee.moviestar.ui.component.MainAppBar
import com.nadhif.hayazee.moviestar.ui.component.MovieListContent
import com.nadhif.hayazee.moviestar.ui.theme.MovieStarTheme


@Composable
fun WatchlistScreen(
    modifier: Modifier = Modifier,
    watchlistScreenViewModel: WatchlistScreenViewModel = viewModel(
        factory = ViewModelFactory(
            RepositoryModule.dummyMovieRepository
        )
    ),
    navigateToSearch: () -> Unit,
    navigateToMovieDetail:(Int) ->Unit
) {
    LaunchedEffect(true){
        watchlistScreenViewModel.getWatchlist()
    }
    val watchlistMovies by watchlistScreenViewModel.watchlistMovies.collectAsState()
    Column {
        MainAppBar(
            navigateSearchScreen = {
                navigateToSearch()
            }
        )
        MovieListContent(
            title = stringResource(id = R.string.menu_watchlist),
            state = watchlistMovies,
            onItemClicked = {
                navigateToMovieDetail(it)
            })
    }
}

@Preview(
    showBackground = true
)
@Composable
fun WatchlistScreenPreview() {
    MovieStarTheme {
        WatchlistScreen(navigateToSearch = {}, navigateToMovieDetail = {})
    }
}