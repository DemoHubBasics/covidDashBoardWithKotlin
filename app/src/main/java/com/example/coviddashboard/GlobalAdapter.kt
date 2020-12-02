package com.example.coviddashboard
import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coviddashboard.model.Global


    class GlobalAdapter(private val context:Context , private val global: Global): RecyclerView.Adapter<GlobalHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlobalHolder {


            return GlobalHolder(LayoutInflater.from(context).inflate(R.layout.global_view, parent, false))
        }



        override fun onBindViewHolder(holder: GlobalHolder, position: Int) {
            val data = global

         //   val globalConfirmed = holder.itemView.findViewById<RecyclerView>(R.id.totalConfirmedGlobalRe)
            val totalConfirmedGlobal = holder.itemView.findViewById<TextView>(R.id.totalConfirmedGlobal)
            totalConfirmedGlobal.text = "${data.totalConfirmed}"


        }

        override fun getItemCount(): Int {
            return 1;
        }

    }






