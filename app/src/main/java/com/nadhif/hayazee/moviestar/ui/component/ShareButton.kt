package com.nadhif.hayazee.moviestar.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nadhif.hayazee.moviestar.ui.theme.MovieStarTheme


@Composable
fun ShareButton(
    modifier: Modifier = Modifier,
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
                imageVector = Icons.Default.Share,
                tint = Color.White,
                contentDescription = stringResource(id = com.nadhif.hayazee.moviestar.R.string.btn_share_cd)
            )
            Text(
                modifier = modifier.padding(horizontal = 8.dp),
                text = stringResource(id = com.nadhif.hayazee.moviestar.R.string.share)
            )
        }


    }

}

@Preview(
    showBackground = true
)
@Composable
fun ShareButtonPreview() {
    MovieStarTheme {
        ShareButton() {

        }
    }
}