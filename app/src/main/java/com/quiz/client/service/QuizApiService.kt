package com.quiz.client.service

import com.quiz.client.model.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface QuizApiService {


    @GET("/api/category/all")
    fun listCategories(): Call<List<String>>

    @GET("/api/question/{category}/{count}")
    fun listQuestions(@Path("category")category:String,@Path("count")count:String):Call<List<Question>>


}