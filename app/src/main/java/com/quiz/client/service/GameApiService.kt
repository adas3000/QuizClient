package com.quiz.client.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GameApiService {


    @Headers("Content-Type: application/json")
    @GET("/api/game/all_connected/{uuid}")
    fun onAllPlayersConnected(@Path("uuid")uuid:String): Call<List<String>>


}