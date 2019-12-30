package com.quiz.client.service

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path


interface OpponentApiService {

    @POST("/api/queue")
    fun goToQueue(@Body serial:String);

    @DELETE("/api/queue/{serial}")
    fun cancelQueue(@Path("serial")serial:String)
}