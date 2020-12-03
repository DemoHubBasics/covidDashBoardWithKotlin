package com.example.coviddashboard

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


import com.example.coviddashboard.model.CountrySummary


class MyAdapter(private val context:Context, private val countrySummaryList: MutableList<CountrySummary>): RecyclerView.Adapter<SummaryHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryHolder {


        return SummaryHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun getItemCount(): Int = countrySummaryList.size

    override fun onBindViewHolder(holder: SummaryHolder, position: Int) {
        val data = countrySummaryList[position]
        val countryView = holder.itemView.findViewById<TextView>(R.id.country)
        val totalConfirmed = holder.itemView.findViewById<TextView>(R.id.totalConfirmed)
        val date = holder.itemView.findViewById<TextView>(R.id.date)
        //val totalConfirmed = holder.itemView.findViewById<TextView>(R.id.totalConfirmed)
        countryView.text = "${data.country}"
        totalConfirmed.text = "${data.totalConfirmed}"
        date.text = "${data.date}"
    }
}
