package com.nadhif.hayazee.moviestar.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nadhif.hayazee.moviestar.BuildConfig
import com.nadhif.hayazee.moviestar.ui.theme.shimmer

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {

    Box(modifier = modifier.padding(4.dp)) {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            AsyncImage(
                model = BuildConfig.IMAGE_URL + imageUrl,
                contentDescription = stringResource(id = com.nadhif.hayazee.moviestar.R.string.show_more_movie_cd),
                modifier = modifier.height(150.dp),
                contentScale = ContentScale.Crop
            )
        }
    }


}


@Composable
fun MovieItemShimmer(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .shimmer()
    ) {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            modifier = modifier.shimmer()
        ) {
            Box(
                modifier = modifier
                    .width(100.dp)
                    .height(150.dp)
                    .background(Color.Gray)
            )
        }
    }
}