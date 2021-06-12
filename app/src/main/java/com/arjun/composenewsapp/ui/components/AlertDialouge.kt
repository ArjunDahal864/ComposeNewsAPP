package com.arjun.composenewsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.GraphicEq
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CustomAlertDialog() {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(false)  }
            Icon(
                Icons.Rounded.GraphicEq,
                modifier = Modifier
                    .height(30.dp)
                    .clickable {
                        openDialog.value = true
                    }
                    .width(30.dp),
                contentDescription = "alert"
            )
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Choose Sources")
                    },

                    confirmButton = {
                        Button(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Done")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Dismiss")
                        }
                    }
                )
            }
        }

    }
}