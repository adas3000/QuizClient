package com.quiz.client.presenter

import com.quiz.client.model.NewRoomRequest
import com.quiz.client.service.OpponentApiService
import com.quiz.client.view.IPlayersCountView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayersCountPresenter : IPlayersCountPresenter {

    val view: IPlayersCountView
    val api:OpponentApiService

    constructor(view: IPlayersCountView,api:OpponentApiService) {
        this.view = view
        this.api = api
    }

    override fun onCountSelect(category: String, questionCount: Int, playerCount: Int) {

        val room = NewRoomRequest()
        room.category = category
        room.playerCount = playerCount
        room.questionCount = questionCount

        val call = api.createNewRoomRequest(room)

        call.enqueue(object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onError(t.message.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    view.onSuccess(response.body().toString())
                }
                else view.onError(response.message())
            }
        })

    }

}