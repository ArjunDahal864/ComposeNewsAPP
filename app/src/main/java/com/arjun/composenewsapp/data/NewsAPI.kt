package com.arjun.composenewsapp.data

import com.arjun.composenewsapp.data.remote.responses.Everything
import com.arjun.composenewsapp.data.remote.responses.Sources
import retrofit2.http.GET
import retrofit2.http.Query

// b33fbcf28f3d479db5a6276f0502523f  api key
interface NewsAPI {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String?,
        @Query("sources") source: String?,
        @Query("q") q: String?,
    ): Everything

    @GET("everything")
    suspend fun getEverything(
        @Query("q") q:String,
        @Query("from") from:String,
        @Query("to") to:String,
        @Query("shortBY") shortBy:String?,
        @Query("domains") domains:String?
    ):Everything

    @GET("sources")
    suspend fun getSources(
        @Query("category") category: String?,
        @Query("language") language:String?,
        @Query("country") country:String?
    ):Sources

}