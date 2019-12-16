package com.quiz.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import butterknife.ButterKnife
import com.quiz.client.service.QuizApiService
import com.quiz.client.task.DoHttpTask
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        retrofit = Retrofit.Builder().baseUrl("http://192.168.0.102:8082")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val quizApiService: QuizApiService = retrofit.create(QuizApiService::class.java)

        val call = quizApiService.listCategories()

        call.enqueue(object:Callback<List<String>>{
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("Failure")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                println("success")
                println(response.body())
                println(response.message())
            }
        })

        //val categories:List<String> = quizApiService.listCategories()
/*

        val rv = rv_Categories
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)



        var adapter = RecyclerViewAdapter(categories)

        rv.adapter = adapter

*/
    }


}
