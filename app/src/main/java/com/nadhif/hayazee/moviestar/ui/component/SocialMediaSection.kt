package com.nadhif.hayazee.moviestar.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.nadhif.hayazee.moviestar.ui.theme.Blue
import com.nadhif.hayazee.moviestar.ui.theme.GreyText
import com.nadhif.hayazee.moviestar.ui.theme.LightNavy

@Composable
fun SocialMediaSection(
    modifier: Modifier = Modifier,
    Icon: @Composable () -> Unit,
    socialMediaLink: String,
    onLinkClicked: (String) -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon()

        Text(
            modifier = modifier
                .padding(horizontal = 12.dp)
                .clickable {
                    onLinkClicked(socialMediaLink)
                },
            text = socialMediaLink,
            color = GreyText,
            fontWeight = FontWeight.SemiBold,
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )

    }

}