package com.nadhif.hayazee.moviestar.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nadhif.hayazee.moviestar.R
import com.nadhif.hayazee.moviestar.ui.screen.movie.MovieRowUiState
import com.nadhif.hayazee.moviestar.ui.theme.MovieStarTheme


@Composable
fun MovieSection(
    modifier: Modifier = Modifier,
    title: String,
    onShowMoreClicked: () -> Unit,
    content: @Composable () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                bottom = 8.dp
            )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Text(
                textAlign = TextAlign.Left,
                text = title,
                fontWeight = FontWeight.Bold,
                color = Color.White

            )
            IconButton(
                onClick = {
                    onShowMoreClicked.invoke()
                }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    tint = Color.White,
                    contentDescription = stringResource(
                        id = R.string.show_more_movie_cd
                    )
                )
            }
        }

        content.invoke()

    }
}


@Preview(
    showBackground = true
)
@Composable
fun MovieSectionPreview() {
    MovieStarTheme {
        MovieSection(
            title = stringResource(id = R.string.popular_movie_title),
            onShowMoreClicked = {},
            content = {
                MovieRow(movies = listOf()) {

                }
            }
        )
    }
}