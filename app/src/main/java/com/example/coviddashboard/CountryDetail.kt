package com.example.coviddashboard

import android.R.string
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coviddashboard.Services.CountryInterface
import com.example.coviddashboard.Services.CountrySpecficDataInterface
import com.example.coviddashboard.model.CountryItem
import com.example.coviddashboard.model.CountrySpecificDataItem
import com.example.coviddashboard.model.CountrySummary
import com.example.coviddashboard.model.Summary
import com.example.coviddashboard.serviveImpl.CountrySpecificDataAdapter
import kotlinx.android.synthetic.main.activity_country_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CountryDetail : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        var rf: Retrofit? = Retrofit.Builder().baseUrl(CountryInterface.BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).build()
        var API = rf?.create(CountryInterface ::class.java)
        var call : Call<List<CountryItem?>?>? = API?.countries

        call?.enqueue(object: Callback<List<CountryItem?>?>{
            override fun onFailure(call: Call<List<CountryItem?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<CountryItem?>?>,
                response: Response<List<CountryItem?>?>
            ) {

                var countrySummaryList : List<CountryItem?>?=
                    response.body()!! as List<CountryItem?>
                var country: Array<CountryItem?> = arrayOfNulls<CountryItem>(countrySummaryList!!.size)

                val arrayList: ArrayList<String> = ArrayList()

                for (item in countrySummaryList.indices){

                    var slug :String = countrySummaryList!![item]!!.slug
                    arrayList.add(slug)


                }
                arrayList.sort()
                var adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_dropdown_item_1line,arrayList)
/*
                var myAdapter =     MyAdapter(baseContext, response.body()!! as MutableList<CountrySummary>)
                myAdapter.notifyDataSetChanged()*/

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                countryDropDown.adapter = adapter

            }

        })

        countryDropDown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var countryName:String = parent?.getItemAtPosition(position) as String
                println("Hello---------------------------" + countryName)
                progressBar.visibility = View.VISIBLE
                var retrofit :Retrofit =  Retrofit.Builder()
                    .baseUrl("https://api.covid19api.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                var API :CountrySpecficDataInterface = retrofit.create(CountrySpecficDataInterface::class.java)

                var call : Call<List<CountrySpecificDataItem?>?>? = API?.getStateData(countryName)
                call?.enqueue(object:Callback<List<CountrySpecificDataItem?>?>{
                    override fun onFailure(call: Call<List<CountrySpecificDataItem?>?>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onResponse(
                        call: Call<List<CountrySpecificDataItem?>?>,
                        response: Response<List<CountrySpecificDataItem?>?>
                    ) {
                        progressBar.visibility = View.GONE
                        var stateList : List<CountrySpecificDataItem?>?=
                            response.body()!! as List<CountrySpecificDataItem?>

                        var country: Array<CountrySpecificDataItem?> = arrayOfNulls<CountrySpecificDataItem>(stateList!!.size)
                        for (i in stateList!!.indices)
                            country[i] = stateList!![i]!!
                        var adapter = ArrayAdapter<CountrySpecificDataItem>(applicationContext, android.R.layout.simple_spinner_dropdown_item,country)

                        var myAdapter = CountrySpecificDataAdapter(baseContext, response.body()!! as MutableList<CountrySpecificDataItem>)
                        myAdapter.notifyDataSetChanged()
                        countrySpecificRecyclerView.adapter = myAdapter
                        countrySpecificRecyclerView.layoutManager = LinearLayoutManager(baseContext);

                    }

                })

            }
            
        }
    }



}