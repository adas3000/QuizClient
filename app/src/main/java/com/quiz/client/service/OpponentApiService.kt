package com.quiz.client.service

import retrofit2.Call
import retrofit2.http.*


interface OpponentApiService {

    @POST("/api/queue/{serial}")
    fun goToQueue(@Path("serial") serial:String): Call<List<String>>

    @DELETE("/api/queue/{serial}")
    fun cancelQueue(@Path("serial")serial:String):Call<List<String>>

    @GET("/api/queue/opponent/{serial}")
    fun getOpponent(@Path("serial")serial:String):Call<List<String>>

}