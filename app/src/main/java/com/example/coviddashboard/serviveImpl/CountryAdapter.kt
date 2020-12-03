package com.example.coviddashboard.serviveImpl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coviddashboard.R
import com.example.coviddashboard.model.CountryItem


class CountryAdapter (private val context: Context, private val countryList: MutableList<CountryItem>): RecyclerView.Adapter<CountryHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return CountryHolder(LayoutInflater.from(context).inflate(R.layout.activity_country_detail, parent, false))
    }

    override fun getItemCount(): Int   = countryList.size

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {

    }
}

class CountryHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

}