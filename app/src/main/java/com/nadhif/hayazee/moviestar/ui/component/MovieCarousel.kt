package com.nadhif.hayazee.moviestar.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nadhif.hayazee.moviestar.BuildConfig
import com.nadhif.hayazee.moviestar.R
import com.nadhif.hayazee.moviestar.ui.screen.movie.MovieRowUiState
import com.nadhif.hayazee.moviestar.ui.theme.GreyText
import com.nadhif.hayazee.moviestar.ui.theme.MovieStarTheme
import com.nadhif.hayazee.moviestar.ui.theme.shimmer
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieCarousel(
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit,
    state: MovieRowUiState
) {

    val pagerState = rememberPagerState()

    Column(modifier = modifier.padding(top = 46.dp)) {

        when (state) {
            is MovieRowUiState.Loading -> {
                MovieCarouselShimmer()
            }
            is MovieRowUiState.Success -> {
                val data = state.movies
                HorizontalPager(
                    count = data.size,
                    state = pagerState,
                    contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
                ) { position ->

                    Box(modifier = modifier.padding(6.dp)) {
                        AsyncImage(
                            modifier = modifier
                                .aspectRatio(16 / 9f)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    onItemClicked(data[position].id ?: -1)
                                },
                            contentScale = ContentScale.Crop,
                            model = BuildConfig.BACKDROP_URL + data[position].backdrop_path,
                            contentDescription = stringResource(id = R.string.menu_movie)
                        )
                    }

                }

                LaunchedEffect(key1 = pagerState.currentPage) {
                    delay(3000)
                    var newPosition = pagerState.currentPage + 1
                    if (newPosition > data.lastIndex) newPosition = 0
                    pagerState.animateScrollToPage(newPosition)
                }

            }
            is MovieRowUiState.Error -> {
                CarouselNotFound(message = state.errorMessage)
            }
        }
    }

}

@Composable
fun CarouselNotFound(
    modifier: Modifier = Modifier,
    message: String
) {
    Column(
        modifier = modifier.aspectRatio(16 / 9f),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, color = GreyText, textAlign = TextAlign.Center)
    }
}

@Composable
fun MovieCarouselShimmer(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .shimmer()

    ) {
        Box(
            modifier = modifier
                .aspectRatio(16 / 9f)
                .background(Color.Gray)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCarouselPreview() {
    MovieStarTheme {
        MovieCarousel(onItemClicked = {}, state = MovieRowUiState.Loading)
    }
}