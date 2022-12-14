package com.nadhif.hayazee.moviestar.ui.component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nadhif.hayazee.moviestar.R

@Composable
fun MainAppBar(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.app_name),
    navigateSearchScreen: () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primarySurface.copy(alpha = 0.95f),
        title = {
            Text(
                text = title,
                fontFamily = FontFamily(Font(R.font.natural)),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = {
                navigateSearchScreen()
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_movie_cd),
                    tint = Color.White
                )
            }
        }
    )
}