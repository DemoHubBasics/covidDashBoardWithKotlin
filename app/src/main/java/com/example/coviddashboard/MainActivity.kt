package com.example.coviddashboard

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coviddashboard.model.CountrySummary
import com.example.coviddashboard.model.Summary
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCountry.setOnClickListener {
            intent = Intent(this, CountryDetail::class.java)
            startActivity(intent)
        }
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(applicationContext.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(applicationContext)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()
        var rf: Retrofit? = Retrofit.Builder().baseUrl(RetrofitInterface.BASE_URL).client(okHttpClient).
        addConverterFactory(GsonConverterFactory.create()).build()
        var API = rf?.create(RetrofitInterface ::class.java)
        var call : Call<Summary?>? = API?.summary

        call?.enqueue(object:Callback<Summary?>{
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

                globalTotalText.text = response.body()?.global?.totalConfirmed.toString()
                totalDeathText.text = response.body()?.global?.totalDeaths.toString()
                totalRecoveredText.text = response.body()?.global?.totalRecovered.toString()
                newConfirmedText.text = response.body()?.global?.newConfirmed.toString()
                newDeathText.text = response.body()?.global?.newDeaths.toString()
                newRecoveredText.text = response.body()?.global?.newRecovered.toString()

            }

        })

    }

    private fun hasNetwork(applicationContext: Context?): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }


}