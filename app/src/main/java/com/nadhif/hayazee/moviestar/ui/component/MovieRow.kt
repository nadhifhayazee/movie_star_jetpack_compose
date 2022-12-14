package com.nadhif.hayazee.moviestar.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nadhif.hayazee.moviestar.model.Movie


@Composable
fun MovieRow(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onItemClicked: (Int) -> Unit,
) {

    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), content = {
        items(movies) { movie ->

            MovieItem(modifier = modifier.clickable {
                onItemClicked.invoke(movie.id ?: -1)
            }, imageUrl = movie.poster_path)
        }
    })
}


@Composable
fun MovieRowLoadingShimmer() {
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), content = {
        items(8) {
            MovieItemShimmer()
        }
    })
}
