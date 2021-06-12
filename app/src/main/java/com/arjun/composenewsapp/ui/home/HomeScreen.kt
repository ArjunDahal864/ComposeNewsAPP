package com.arjun.composenewsapp.ui.home

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arjun.composenewsapp.R
import com.arjun.composenewsapp.data.remote.responses.Article
import com.arjun.composenewsapp.ui.components.*
import com.arjun.composenewsapp.ui.theme.DarkBlue
import com.arjun.composenewsapp.ui.theme.LightGrey
import com.google.accompanist.coil.rememberCoilPainter


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    Surface(
        color = MaterialTheme.colors.background
    ) {

        val loadError = remember {
            viewModel.loadError
        }
        val isLoading = remember {
            viewModel.isLoading
        }

        val newsList = remember {
            viewModel.newsList
        }
        val category = remember {
            viewModel.category
        }
        val selectedIndex = remember {
            mutableStateOf(0)
        }

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CustomAlertDialog()
                SearchBar(modifier = Modifier.padding(8.dp), hint = "search anything ", onSearch = {
                    viewModel.getEverything(it)
                })
            }

            LazyRow {
                items(category.size) { index ->
                    Chip(
                        text = category[index],
                        selected = selectedIndex.value == index,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                selectedIndex.value = index
                                viewModel.onQueryChanged(query = category[selectedIndex.value])
                            }
                    )
                }
            }
            LazyColumn {
                items(newsList.value.size) { index ->
                    News(
                        article = newsList.value.reversed()[index],
                        modifier = Modifier,
                        navController = navController
                    )
                }
            }

            Box(
                contentAlignment = Center,
                modifier = Modifier.fillMaxSize()
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }
                if (loadError.value.isNotEmpty()) {
                    RetrySection(error = loadError.value) {
                        viewModel.onQueryChanged(category[selectedIndex.value])
                    }
                }
            }
        }
    }
}



