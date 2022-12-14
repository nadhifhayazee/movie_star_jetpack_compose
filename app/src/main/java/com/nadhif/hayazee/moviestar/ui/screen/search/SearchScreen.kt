package com.nadhif.hayazee.moviestar.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nadhif.hayazee.moviestar.module.RepositoryModule
import com.nadhif.hayazee.moviestar.ui.ViewModelFactory
import com.nadhif.hayazee.moviestar.ui.component.MovieListContent
import com.nadhif.hayazee.moviestar.ui.component.SearchBar
import com.nadhif.hayazee.moviestar.ui.theme.MovieStarTheme


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit,
    onItemClicked: (Int) -> Unit,
    searchScreenViewModel: SearchScreenViewModel = viewModel(
        factory = ViewModelFactory(
            RepositoryModule.dummyMovieRepository
        )
    )
) {

    var query by searchScreenViewModel.query


    Column {
        SearchBar(
            value = query,
            onValueChange = { newValue ->
                query = newValue
                searchScreenViewModel.searchMovieByName(query)
            }, onBackPress = {
                onBackPress()
            }, onDeleteQuery = {
                query = ""
                searchScreenViewModel.searchMovieByName(query)
            })

        searchScreenViewModel.searchResults.collectAsState().value.let {
            MovieListContent(state = it, onItemClicked = { onItemClicked(it) })
        }


    }

}

@Preview(
    showBackground = true
)
@Composable
fun SearchScreenPreview() {
    MovieStarTheme {
        SearchScreen(onBackPress = { /*TODO*/ }, onItemClicked = {})
    }
}