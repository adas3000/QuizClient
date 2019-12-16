package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.quiz.client.adapter.RecyclerViewAdapter
import com.quiz.client.service.QuizApiService
import com.quiz.client.view.ICategoryView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),ICategoryView  {

    lateinit var retrofit: Retrofit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        retrofit = Retrofit.Builder().baseUrl("http://192.168.0.102:8082")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val rv = rv_Categories
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val quizApiService: QuizApiService = retrofit.create(QuizApiService::class.java)

        val call = quizApiService.listCategories()

        val context = this

        call.enqueue(object:Callback<List<String>>{
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("Failure")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                var adapter = RecyclerViewAdapter(response.body()!!.toList(),context)
                rv.adapter = adapter
            }
        })


    }

    override fun onCategoryClick(category: String) {
        Toast.makeText(this,category,Toast.LENGTH_LONG).show()
    }

}
