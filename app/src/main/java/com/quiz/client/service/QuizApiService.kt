package com.quiz.client.service

import retrofit2.Call
import retrofit2.http.GET


interface QuizApiService {


    @GET("/api/category/all")
    fun listCategories(): Call<List<String>>

}