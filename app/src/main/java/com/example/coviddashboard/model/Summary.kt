package com.example.coviddashboard.model


import com.google.gson.annotations.SerializedName

data class Summary(
    @SerializedName("Countries")
    val countries: List<CountrySummary>,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Global")
    val global: Global,
    @SerializedName("Message")
    val message: String
)