package com.nadhif.hayazee.moviestar.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nadhif.hayazee.moviestar.ui.theme.GreyText
import com.nadhif.hayazee.moviestar.ui.theme.MovieStarTheme


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onBackPress: () -> Unit,
    onDeleteQuery: () -> Unit
) {
    Box(modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        TopAppBar(
            modifier = modifier.clip(RoundedCornerShape(4.dp)),
            backgroundColor = MaterialTheme.colors.primarySurface,
            title = {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { onValueChange(value) }),
                    value = value,
                    onValueChange = { newValue -> onValueChange(newValue) },
                    placeholder = {
                        Text(
                            modifier = modifier.padding(vertical = 4.dp),
                            textAlign = TextAlign.Left,
                            text = stringResource(id = com.nadhif.hayazee.moviestar.R.string.search_placeholder),
                            color = GreyText,
                        )
                    },

                    )
            },
            navigationIcon = {
                IconButton(onClick = {
                    onBackPress.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = com.nadhif.hayazee.moviestar.R.string.back_button_cd),
                        tint = Color.White
                    )
                }
            },
            actions = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = {
                        onDeleteQuery()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(id = com.nadhif.hayazee.moviestar.R.string.clear_query_cd),
                            tint = Color.White
                        )
                    }
                }
            }
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SearchBarPreview() {

    MovieStarTheme {
        SearchBar(
            value = "Search",
            onValueChange = { /*TODO*/ },
            onBackPress = {},
            onDeleteQuery = {})
    }
}