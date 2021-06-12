package com.arjun.composenewsapp.data.remote.responses

data class Everything(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)