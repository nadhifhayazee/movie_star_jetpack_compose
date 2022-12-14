package com.nadhif.hayazee.moviestar.ui.screen.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadhif.hayazee.moviestar.R
import com.nadhif.hayazee.moviestar.ui.component.SocialMediaSection
import com.nadhif.hayazee.moviestar.ui.theme.GreyText
import com.nadhif.hayazee.moviestar.ui.theme.LightNavy
import com.nadhif.hayazee.moviestar.ui.theme.MovieStarTheme


@Composable
fun AboutScreen(modifier: Modifier = Modifier, context: Context) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            modifier = modifier
                .size(160.dp)
                .clip(CircleShape)
                .border(width = 2.dp, color = LightNavy, shape = CircleShape),
            painter = painterResource(id = R.drawable.my_photo),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(
                id = R.string.my_photo_cd
            )
        )


        Text(
            modifier = modifier.padding(vertical = 12.dp, horizontal = 16.dp),
            text = stringResource(id = R.string.my_name),
            fontFamily = FontFamily(Font(R.font.natural)),
            color = GreyText,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )


        Column(modifier = modifier.padding(vertical = 20.dp)) {
            SocialMediaSection(
                Icon = {
                    Image(
                        modifier = modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.ic_gmail),
                        contentDescription = stringResource(
                            id = R.string.social_media_icon_cd
                        )
                    )
                }, socialMediaLink = stringResource(id = R.string.my_email), onLinkClicked = {
                    sendEmail(context = context, emailAddress = it)
                })

            SocialMediaSection(Icon = {
                Image(
                    modifier = modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.ic_github),
                    contentDescription = stringResource(
                        id = R.string.social_media_icon_cd
                    )
                )
            }, socialMediaLink = stringResource(id = R.string.my_github), onLinkClicked = {
                openLinkToBrowser(context = context, link = it)
            })
        }


    }
}

private fun sendEmail(context: Context, emailAddress: String) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:$emailAddress")
    context.startActivity(intent)
}

private fun openLinkToBrowser(context: Context, link: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(link)
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    MovieStarTheme {
        AboutScreen(context = LocalContext.current)
    }
}