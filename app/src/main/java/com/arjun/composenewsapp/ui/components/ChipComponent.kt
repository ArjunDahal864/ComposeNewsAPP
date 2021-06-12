package com.arjun.composenewsapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arjun.composenewsapp.ui.theme.DarkBlue
import com.arjun.composenewsapp.ui.theme.LightGrey


@Composable
fun Chip(text: String, selected: Boolean, modifier: Modifier) {
    Card(
        modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp),

        elevation = 8.dp,
        shape = RoundedCornerShape(50),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> DarkBlue
                else -> LightGrey
            }
        )
    ) {
        Text(text = text, modifier = modifier.padding(horizontal = 8.dp))
    }
}

