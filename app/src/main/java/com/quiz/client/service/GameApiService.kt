package com.quiz.client.service

import com.quiz.client.model.Score
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path

interface GameApiService {


    @Headers("Content-Type: application/json")
    @GET("/api/game/all_connected/{uuid}")
    fun onAllPlayersConnected(@Path("uuid")uuid:String): Call<List<String>>


    @Headers("Content-Type: application/json")
    @PUT("/api/game/update/score/{uuid}/{serial}/{howmany}")
    fun updateDeviceScoreInGame(@Path("uuid")uuid:String,@Path("serial")serial:String,
                                @Path("howmany")howmany:String): Call<String>

    @Headers("Content-Type: application/json")
    @PUT("/api/game/update/answer_finished/{uuid}/{serial}")
    fun updateDeviceFinishedAnsweringToQuestion(@Path("uuid")uuid:String,@Path("serial")serial:String):Call<String>


    @Headers("Content-Type: application/json")
    @GET("/api/game/all/answered/{uuid}")
    fun checkAllDevicesAnswered(@Path("uuid")uuid:String):Call<Boolean>


    @Headers("Content-Type: application/json")
    @GET("/api/game/find/scores/{uuid}")
    fun findScoresByUUID(@Path("uuid")uuid:String):Call<List<Score>>

}