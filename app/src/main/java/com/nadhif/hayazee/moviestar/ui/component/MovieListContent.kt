package com.nadhif.hayazee.moviestar.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadhif.hayazee.moviestar.model.Movie
import com.nadhif.hayazee.moviestar.ui.screen.movie.MovieRowUiState
import com.nadhif.hayazee.moviestar.ui.theme.GreyText
import com.nadhif.hayazee.moviestar.ui.theme.MovieStarTheme
import com.nadhif.hayazee.moviestar.ui.theme.shimmer


@Composable
fun MovieListContent(
    modifier: Modifier = Modifier,
    title: String? = null,
    state: MovieRowUiState,
    onItemClicked: (Int) -> Unit
) {

    title?.let {
        Text(
            modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            text = title,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }

    when (state) {

        is MovieRowUiState.Loading -> {
            MovieListShimmer()
        }
        is MovieRowUiState.Success -> {
            ShowMovies(movieList = state.movies, onItemClicked = { onItemClicked(it) })
        }
        is MovieRowUiState.Error -> {
            MovieNotFound()
        }

    }


}

@Composable
fun ShowMovies(
    modifier: Modifier = Modifier,
    movieList: List<Movie>,
    onItemClicked: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxHeight(),
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        content = {
            items(movieList) { movie ->
                MovieItem(
                    modifier = modifier
                        .aspectRatio(3 / 4f)
                        .clickable {
                            onItemClicked(movie.id ?: -1)
                        }, imageUrl = movie.poster_path
                )
            }
        }
    )
}

@Composable
fun MovieListShimmer(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxHeight(),
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        content = {
            items(10) { position ->
                Box(
                    modifier = modifier
                        .padding(8.dp)
                        .shimmer()
                ) {
                    Box(
                        modifier = modifier
                            .aspectRatio(3 / 4f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Gray)
                    )
                }
            }
        }
    )
}

@Composable
fun MovieNotFound(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = modifier
                .size(70.dp)
                .padding(vertical = 12.dp),
            imageVector = Icons.Default.List,
            contentDescription = stringResource(id = com.nadhif.hayazee.moviestar.R.string.no_found_icon_cd),
            tint = GreyText
        )
        Text(
            text = stringResource(id = com.nadhif.hayazee.moviestar.R.string.no_movie_found),
            color = GreyText,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun MovieListContent() {

    MovieStarTheme {
        MovieListContent(
            title = stringResource(id = com.nadhif.hayazee.moviestar.R.string.menu_watchlist),
            state = MovieRowUiState.Loading,
            onItemClicked = {})
    }
}