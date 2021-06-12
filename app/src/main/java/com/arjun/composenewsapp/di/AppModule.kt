package com.arjun.composenewsapp.di

import com.arjun.composenewsapp.data.NewsAPI
import com.arjun.composenewsapp.repository.NewsRepository
import com.arjun.composenewsapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor

import com.arjun.composenewsapp.BuildConfig
import com.arjun.composenewsapp.utils.Constants.API_KEY
import dagger.Provides
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideNewsRepository(api: NewsAPI) = NewsRepository(api)


    @Singleton
    @Provides
    fun provideNewsApi(): NewsAPI {
        val client = OkHttpClient.Builder()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val url = chain
                            .request()
                            .url
                            .newBuilder()
                            .addQueryParameter("apiKey", API_KEY)
                            .build()
                        chain.proceed(chain.request().newBuilder().url(url).build())
                    }
                    .build()
            )
            .build()
            .create(NewsAPI::class.java)
    }


}