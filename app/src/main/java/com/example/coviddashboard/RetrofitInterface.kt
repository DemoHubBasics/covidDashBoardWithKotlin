package com.example.coviddashboard

import com.example.coviddashboard.model.CountrySummary
import com.example.coviddashboard.model.Summary
import retrofit2.Call
import retrofit2.http.GET


interface RetrofitInterface
{
    @get:GET("summary")
    val summary : Call<Summary?>?
    companion object{
        const val BASE_URL = "https://api.covid19api.com"
    }
}