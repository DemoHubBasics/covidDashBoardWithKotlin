package com.example.coviddashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coviddashboard.model.CountrySummary
import com.example.coviddashboard.model.Global
import com.example.coviddashboard.model.Summary
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        var rf: Retrofit? = Retrofit.Builder().baseUrl(RetrofitInterface.BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).build()
        var API = rf?.create(RetrofitInterface ::class.java)
        var call : Call<Summary?>? = API?.summary
        call?.enqueue(object: Callback<Summary?> {
            override fun onFailure(call: Call<Summary?>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                    call: Call<Summary?>,
                    response: Response<Summary?>
            ) {
                var summary : Summary?=response.body() as Summary
                var countrySummaryList : List<CountrySummary?>?=
                        response.body()!!.countries as List<CountrySummary?>
                var country: Array<CountrySummary?> = arrayOfNulls<CountrySummary>(countrySummaryList!!.size)
                for (i in countrySummaryList!!.indices)
                    country[i] = countrySummaryList!![i]!!
                var adapter = ArrayAdapter<CountrySummary>(applicationContext, android.R.layout.simple_dropdown_item_1line,country)

                var myAdapter =     MyAdapter(baseContext, response.body()!!.countries as MutableList<CountrySummary>)
                myAdapter.notifyDataSetChanged()
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(baseContext);

                var globalAdapter =     GlobalAdapter(baseContext, response.body()!!.global as Global)
                globalAdapter.notifyDataSetChanged()
                totalConfirmedGlobalRe.adapter = globalAdapter
                totalConfirmedGlobalRe.layoutManager = LinearLayoutManager(baseContext);



            }

        })
    }
}