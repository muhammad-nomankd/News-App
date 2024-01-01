package com.example.newsapp

import android.os.Bundle

import androidx.activity.ComponentActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
         lateinit var recyclerview:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
       recyclerview= findViewById(R.id.recyclerview1)
        getnews()
    }

    fun getnews() {
        val news = NewsService.newsInstance.FetchHeadlines("in", Constant.API_key)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val NewsResponse = response.body()
                NewsResponse?.let {
                    val recyclerview: RecyclerView = findViewById(R.id.recyclerview1)
                    recyclerview.adapter = NewsAdapter(this@MainActivity, NewsResponse.articles)
                }
                recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                println(t.message)
            }

        })
    }


}