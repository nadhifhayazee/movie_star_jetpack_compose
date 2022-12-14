package com.nadhif.hayazee.moviestar.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nadhif.hayazee.moviestar.ui.theme.MovieStarTheme

@Composable
fun WatchlistButton(
    modifier: Modifier = Modifier,
    isWatchlist: Boolean = true,
    onButtonClicked: () -> Unit
) {
    Button(
        modifier = modifier
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primarySurface),
        onClick = { onButtonClicked() }) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                modifier = modifier
                    .size(16.dp),
                imageVector = if (isWatchlist) Icons.Default.Check else Icons.Default.Add,
                tint = if (isWatchlist) MaterialTheme.colors.secondary else Color.White,
                contentDescription = stringResource(id = com.nadhif.hayazee.moviestar.R.string.icon_is_watchlist_cd)
            )
            Text(
                modifier = modifier.padding(horizontal = 8.dp),
                text = stringResource(id = com.nadhif.hayazee.moviestar.R.string.menu_watchlist)
            )
        }


    }

}

@Preview(
    showBackground = true
)
@Composable
fun WatchlistButtonPreview() {
    MovieStarTheme() {
        WatchlistButton() {

        }
    }
}