package com.quiz.client.service

import com.quiz.client.model.Device
import com.quiz.client.model.NewRoomRequest
import com.quiz.client.model.Question
import retrofit2.Call
import retrofit2.http.*


interface OpponentApiService {

    @Headers("Content-Type: application/json")
    @POST("/api/queue/{serial}")
    fun goToQueue(@Path("serial") serial:String): Call<List<String>>

    @Headers("Content-Type: application/json")
    @POST("/api/queue/join/{serial}/{nickname}")
    fun joinToQueue(@Path("serial")serial:String,@Path("nickname")nickname:String):Call<List<String>>

    @Headers("Content-Type: application/json")
    @DELETE("/api/queue/{serial}")
    fun cancelQueue(@Path("serial")serial:String):Call<List<String>>

    @Headers("Content-Type: application/json")
    @GET("/api/queue/opponent/{serial}")
    fun getOpponent(@Path("serial")serial:String):Call<List<String>>

    @Headers("Content-Type: application/json")
    @GET("/api/game/{uuid}")
    fun findQuestionList(@Path("uuid")uuid:String):Call<List<Question>>

    @Headers("Content-Type: application/json")
    @POST("/api/game/room")
    fun createNewRoomRequest(@Body newRoomRequest: NewRoomRequest):Call<String>
    
}