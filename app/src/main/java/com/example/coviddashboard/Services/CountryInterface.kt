package com.example.coviddashboard.Services

import com.example.coviddashboard.model.Country
import com.example.coviddashboard.model.CountryItem
import com.example.coviddashboard.model.Summary
import retrofit2.Call
import retrofit2.http.GET

interface CountryInterface {

    @get:GET("countries")
    val countries: Call<List<CountryItem?>?>
    companion object{
        const val BASE_URL = "https://api.covid19api.com"
    }
}