package com.nadhif.hayazee.moviestar.ui.screen.movie_detail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.nadhif.hayazee.moviestar.BuildConfig
import com.nadhif.hayazee.moviestar.R
import com.nadhif.hayazee.moviestar.module.RepositoryModule
import com.nadhif.hayazee.moviestar.ui.ViewModelFactory
import com.nadhif.hayazee.moviestar.ui.component.MovieItem
import com.nadhif.hayazee.moviestar.ui.component.ShareButton
import com.nadhif.hayazee.moviestar.ui.component.WatchlistButton
import com.nadhif.hayazee.moviestar.ui.theme.GreyText
import com.nadhif.hayazee.moviestar.ui.theme.MovieStarTheme
import com.nadhif.hayazee.moviestar.util.DateUtil


@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movieId: Int,
    navigateBack: () -> Unit,
    movieDetailViewModel: MovieDetailViewModel = viewModel(
        factory = ViewModelFactory(
            RepositoryModule.dummyMovieRepository
        )
    ),
    context: Context
) {
    val movieDetailUiState by movieDetailViewModel.movieDetailUiState.collectAsState()
    Column {
        TopAppBar(
            title = {
                Text(
                    text = movieDetailUiState.movie?.title ?: "",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navigateBack.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button_cd),
                        tint = Color.White
                    )
                }
            },
        )


        if (movieDetailUiState.isLoading) {
            LaunchedEffect(movieDetailUiState) {
                movieDetailViewModel.getMovieById(movieId)
            }
        }
        movieDetailUiState.movie?.let { movie ->

            Column(modifier = modifier.verticalScroll(rememberScrollState())) {
                AsyncImage(
                    modifier = modifier.aspectRatio(16 / 9f),
                    model = BuildConfig.BACKDROP_URL + movie.backdrop_path,
                    contentDescription = stringResource(id = R.string.menu_movie)
                )

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp, bottom = 16.dp, top = 20.dp)
                ) {

                    MovieItem(
                        imageUrl = movie.poster_path,
                        modifier = modifier
                            .width(80.dp)
                            .height(100.dp)
                    )

                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = movie.title ?: "",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            modifier = modifier.padding(top = 4.dp),
                            text = movie.genres?.joinToString(separator = ", ") ?: "",
                            color = GreyText,
                            fontSize = 12.sp
                        )
                        Text(
                            modifier = modifier.padding(top = 4.dp),
                            text = DateUtil.convertDate(movie.release_date ?: ""),
                            color = GreyText,
                            fontSize = 12.sp
                        )
                    }

                }

                Text(
                    modifier = modifier.padding(horizontal = 16.dp),
                    text = movie.overview ?: "",
                    color = GreyText,
                    fontSize = 14.sp
                )

                Row(
                    modifier = modifier
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {


                    WatchlistButton(
                        isWatchlist = movieDetailUiState.isWatchListed,
                        onButtonClicked = {
                            movieDetailViewModel.addOrRemoveMovieFromWatchlist(
                                movie.id ?: -1
                            )
                        }
                    )

                    ShareButton(onButtonClicked = {
                        shareMovie(
                            context = context,
                            title = movie.title ?: "",
                            overview = movie.overview ?: ""
                        )
                    })

                }

            }

        }

    }

}

private fun shareMovie(context: Context, title: String, overview: String) {

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(
            Intent.EXTRA_SUBJECT,
            context.getString(R.string.share_movie_message, title, overview)
        )
        putExtra(
            Intent.EXTRA_TEXT,
            context.getString(R.string.share_movie_message, title, overview)
        )
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(
                R.string.share_movie_message, title, overview
            )
        )
    )
}


@Preview(
    showBackground = true
)
@Composable
fun MovieDetailScreenPreview() {

    MovieStarTheme {
        MovieDetailScreen(movieId = 12, navigateBack = { }, context = LocalContext.current)
    }

}