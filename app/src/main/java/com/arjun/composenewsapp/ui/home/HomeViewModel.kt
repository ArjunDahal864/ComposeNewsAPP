package com.arjun.composenewsapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arjun.composenewsapp.data.remote.responses.Article
import com.arjun.composenewsapp.repository.NewsRepository
import com.arjun.composenewsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")
    var newsList = mutableStateOf<List<Article>>(listOf())
    var query = mutableStateOf("")
    init {
        loadHeadlines(query = query.value)
    }

    val category = listOf(
        "United Arab Emirates",
        "Argentina",
        "Austria",
        "Australia",
        "Belgium",
        "Bulgaria",
        "Brazil",
        "Canada",
        "Switzerland",
        "China",
        "Colombia",
        "Cuba",
        "Czechia",
        "Germany",
        "Egypt",
        "France",
        "United Kingdom",
        "Greece",
        "Hong Kong",
        "Hungary",
        "Indonesia",
        "Ireland",
        "Israel",
        "India",
        "Italy",
        "Japan",
        "Korea (South)",
        "Lithuania",
        "Latvia",
        "Morocco",
        "Mexico",
        "Malaysia",
        "Nigeria",
        "Netherlands",
        "Norway",
        "New Zealand",
        "Philippines",
        "Poland",
        "Portugal",
        "Romania",
        "Serbia",
        "Russian Federation",
        "Saudi Arabia",
        "Sweden",
        "Singapore",
        "Slovenia",
        "Slovakia",
        "Thailand",
        "Turkey",
        "Taiwan",
        "Ukraine",
        "United States of America",
        "Venezuela",
        "South Africa",
    )

    fun onQueryChanged(query: String){
        val value = checkSelectedCountry(query)
        loadHeadlines(query= value)
    }


   private fun checkSelectedCountry(selectedCountry: String): String {
        return when (selectedCountry) {
            "United Arab Emirates" -> "ae"
            "Argentina" -> "ar"
            "Austria" -> "at"
            "Australia" -> "au"
            "Belgium" -> "be"
            "Bulgaria" -> "bg"
            "Brazil" -> "br"
            "Canada"-> "ca"
            "Switzerland" -> "ch"
            "China" -> "cn"
            "Colombia" -> "co"
            "Cuba" -> "cu"
            "Czechia" -> "cz"
            "Germany" -> "de"
            "Egypt" -> "eg"
            "France" -> "fr"
            "United Kingdom" -> "gb"
            "Greece" -> "gr"
            "Hong Kong" -> "hk"
            "Hungary" -> "hu"
            "Indonesia",-> "id"
            "Ireland" -> "ie"
            "Israel" -> "il"
            "India" -> "in"
            "Italy"-> "it"
            "Japan" -> "jp"
            "Korea (South)" -> "kr"
            "Lithuania" -> "lt"
            "Latvia" -> "lv"
            "Morocco" -> "ma"
            "Mexico" -> "mx"
            "Malaysia" -> "my"
            "Nigeria" -> "ng"
            "Netherlands" -> "nl"
            "Norway" -> "no"
            "New Zealand" -> "nz"
            "Philippines"-> "ph"
            "Poland" -> "pl"
            "Portugal" -> "pt"
            "Romania" -> "ro"
            "Serbia" -> "rs"
            "Russian Federation" -> "ru"
            "Saudi Arabia" -> "sa"
            "Sweden" -> "se"
            "Singapore" -> "sg"
            "Slovenia" -> "si"
            "Slovakia" -> "sk"
            "Thailand" -> "th"
            "Turkey" -> "tr"
            "Taiwan" -> "tw"
            "Ukraine" -> "ua"
            "United States of America" -> "us"
            "Venezuela" -> "ve"
            else -> "za"
        }
    }



    private fun loadHeadlines(query:String) {
        isLoading.value = true
        viewModelScope.launch {
            when (val result = repository.getTopHeadlines(query)) {
                is Resource.Success -> {
                    result.data!!.articles.forEach {
                        newsList.value += it
                    }
                    loadError.value = ""
                    isLoading.value = false
                }
                is Resource.Error -> {
                    isLoading.value = false
                    loadError.value = result.message!!
                }
                is Resource.Loading -> {
                    isLoading.value = true
                    loadError.value = ""
                }
                is Resource.Ideal -> {
                    isLoading.value = false
                    loadError.value = ""
                }

            }
        }
    }
    @SuppressLint("SimpleDateFormat")
    fun getEverything(query:String){
        isLoading.value = true
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        viewModelScope.launch{
            when(val  result = repository.getEverything(query,from = currentDate.toString(),to = currentDate.toString())){
                is Resource.Success -> {
                    result.data!!.articles.forEach {
                        newsList.value += it
                    }
                    loadError.value = ""
                    isLoading.value = false
                }
                is Resource.Error -> {
                    isLoading.value = false
                    loadError.value = result.message!!
                }
                is Resource.Loading -> {
                    isLoading.value = true
                    loadError.value = ""
                }
                is Resource.Ideal -> {
                    isLoading.value = false
                    loadError.value = ""
                }
            }
        }
    }
}