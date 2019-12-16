package com.quiz.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.quiz.client.service.QuizApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondActivity : AppCompatActivity() {

    lateinit var retrofit:Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent :Intent= intent

        val category:String = intent.getStringExtra("category")

        Toast.makeText(this,category,Toast.LENGTH_LONG).show()

        retrofit = Retrofit.Builder().baseUrl("http://192.168.0.102:8082")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val quizApiService: QuizApiService = retrofit.create(QuizApiService::class.java)



    }
}
