package com.arjun.composenewsapp.repository

import com.arjun.composenewsapp.data.NewsAPI
import com.arjun.composenewsapp.data.remote.responses.Everything
import com.arjun.composenewsapp.data.remote.responses.Sources
import com.arjun.composenewsapp.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class NewsRepository @Inject constructor(
    private val api: NewsAPI
) {
    suspend fun getTopHeadlines(
        country: String,
        category: String? = null,
        sources: String? = null,
        q: String? = null
    ): Resource<Everything> {
        val response = try {
            api.getTopHeadlines(category = category, source = sources, country = country, q = q)
        } catch (e: Exception) {
            return Resource.Error(e.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getEverything(
        q: String,
        from: String,
        to: String,
        shortBy: String? = null,
        domains: String? = null
    ): Resource<Everything> {
        val response = try {
            api.getEverything(q = q,from = from,to=to,shortBy=shortBy,domains = domains)
        } catch (e: Exception) {
            return Resource.Error("Got an unknown error.")
        }
        return Resource.Success(response)
    }

    suspend fun getSources(
        country: String ?= null,
        language:String ?= null,
        category: String? = null

    ): Resource<Sources> {
        val response = try {
            api.getSources(country= country, category= category, language = language)
        } catch (e: Exception) {
            return Resource.Error("Got an unknown error.")
        }
        return Resource.Success(response)
    }


}