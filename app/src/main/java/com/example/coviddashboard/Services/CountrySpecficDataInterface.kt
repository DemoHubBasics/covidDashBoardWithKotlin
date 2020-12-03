package com.example.coviddashboard.Services

import com.example.coviddashboard.model.CountryItem
import com.example.coviddashboard.model.CountrySpecificDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

public interface CountrySpecficDataInterface {

   @GET("live/country/{country}")
   fun getStateData(@Path("country")country : String):Call<List<CountrySpecificDataItem?>?>
}