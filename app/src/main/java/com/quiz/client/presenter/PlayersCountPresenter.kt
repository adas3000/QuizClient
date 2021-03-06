package com.quiz.client.presenter

import android.util.Log
import com.quiz.client.model.NewRoomRequest
import com.quiz.client.model.Question
import com.quiz.client.service.QueueApiService
import com.quiz.client.util.QuestionListKeeper
import com.quiz.client.util.getApplicationToken
import com.quiz.client.view.IPlayersCountView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayersCountPresenter : IPlayersCountPresenter {

    val view: IPlayersCountView
    val api: QueueApiService

    companion object {
        private val TAG: String = "PlayersCountPresenter"
    }

    constructor(view: IPlayersCountView, api: QueueApiService) {
        this.view = view
        this.api = api
    }

    override fun onGoForQuestionList(uuid: String) {

        val call = api.findQuestionList(uuid)

        call.enqueue(object:Callback<List<Question>>{

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                println("failure:"+t.message)
                view.onError("Cannot connect")
            }

            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {

                if(response.isSuccessful){
                    println("success")
                    QuestionListKeeper.questionListKeeper = response.body()!!
                    view.onSuccess(uuid)
                }
                else{
                    println("response failure,MSG:"+response.message()+",CODE:"+response.code())
                    view.onError("Question List error")
                }

            }

        })


    }

    override fun onCountSelect(category: String, questionCount: Int, playerCount: Int) {

        val room = NewRoomRequest()
        room.category = category
        room.playerCount = playerCount
        room.questionCount = questionCount
        room.serial = getApplicationToken()


        val call = api.createNewRoomRequest(room)

        call.enqueue(object : Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                view.onError(t.message.toString())
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    onGoForQuestionList(response.body()!![0])
                } else view.onError(response.message())
            }
        })

    }

    override fun onJoinToQueue(nickname: String) {

        val call = api.joinToQueue(getApplicationToken(), nickname)

        call.enqueue(object : Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                view.onError(t.message.toString())
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                Log.d(TAG, response.message())
                if (!response.isSuccessful) {
                    view.onError(response.body().toString())
                }
            }
        })

    }

}