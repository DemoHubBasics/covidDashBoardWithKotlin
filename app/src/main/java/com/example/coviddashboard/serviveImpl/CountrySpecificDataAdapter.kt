package com.example.coviddashboard.serviveImpl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coviddashboard.R
import com.example.coviddashboard.model.CountryItem
import com.example.coviddashboard.model.CountrySpecificDataItem
import kotlinx.android.synthetic.main.country_specific_view.view.*

class CountrySpecificDataAdapter (private val context: Context, private val stateList: MutableList<CountrySpecificDataItem>): RecyclerView.Adapter<CountrySpecificDataHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountrySpecificDataHolder {
        return CountrySpecificDataHolder(LayoutInflater.from(context).inflate(R.layout.country_specific_view, parent, false))
    }

    override fun getItemCount(): Int = stateList.size

    override fun onBindViewHolder(holder: CountrySpecificDataHolder, position: Int) {
        val data = stateList[position]
        val countryDeath = holder.itemView.findViewById<TextView>(R.id.countryDeath)
        val countryActive = holder.itemView.findViewById<TextView>(R.id.countryActive)
        val countryConfirmed = holder.itemView.findViewById<TextView>(R.id.countryConfirmed)
        val countryRecovered = holder.itemView.findViewById<TextView>(R.id.countryRecovered)
        //val totalConfirmed = holder.itemView.findViewById<TextView>(R.id.totalConfirmed)
        countryDeath.text = "${data.deaths}"
        countryActive.text = "${data.active}"
        countryConfirmed.text = "${data.confirmed}"
        countryRecovered.text = "${data.recovered}"



    }
}

class CountrySpecificDataHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

}
