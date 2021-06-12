package com.arjun.composenewsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arjun.composenewsapp.data.remote.responses.Article
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun News(
    article: Article,
    modifier: Modifier,
    navController: NavController
) {
    Card(modifier = Modifier.padding(8.dp), elevation = 10.dp, backgroundColor = Color.White) {
        Column {
            Image(
                painter = rememberCoilPainter(request = article.urlToImage),
                contentDescription = article.author,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(shape = RoundedCornerShape(percent = 8))
                    .fillMaxSize()
                    .height(250.dp)
                    .shadow(elevation = 10.dp)

            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = article.title.toString(), fontSize = 18.sp)
                Text(text = article.description.toString(), fontStyle = FontStyle.Italic)
            }

        }

    }

}
