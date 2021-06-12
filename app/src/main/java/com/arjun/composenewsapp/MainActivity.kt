package com.arjun.composenewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arjun.composenewsapp.ui.home.HomeScreen
import com.arjun.composenewsapp.ui.theme.ComposeNewsAPPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNewsAPPTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "news_home_page" ){
                    composable("news_home_page"){
                        HomeScreen(navController = navController)
                    }
                }
            }
        }
    }
}