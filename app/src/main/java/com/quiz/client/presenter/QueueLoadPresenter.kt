package com.quiz.client.presenter

import com.quiz.client.service.OpponentApiService
import com.quiz.client.util.getApplicationToken
import com.quiz.client.view.IQueueLoadView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QueueLoadPresenter : IQueueLoadPresenter {

    val iQueueLoadView:IQueueLoadView
    val opponentApiService: OpponentApiService

    constructor(iQueueLoadView:IQueueLoadView,opponentApiService: OpponentApiService){
        this.iQueueLoadView = iQueueLoadView
        this.opponentApiService = opponentApiService
    }


    override fun onSearchOpponent() {

        val call = opponentApiService.getOpponent(getApplicationToken())

        call.enqueue(object:Callback<List<String>>{
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("failure:"+t.message)
                iQueueLoadView.onError("Cannot connect")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if(response.code()==100){
                    println("Try again")
                }
                else if(response.isSuccessful){
                    println("success")
                    println("Response body:"+response.body()!!.toList())
                }
                else{
                    println("response failure")
                    println("body:"+response.body()+",code:"+response.code())
                    iQueueLoadView.onError("Cannot connect")
                }
            }
        })


    }

    override fun onGoToQueue() {

        val call = opponentApiService.goToQueue(getApplicationToken())

        call.enqueue(object: Callback<List<String>>{
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("failure:"+t.message)
                iQueueLoadView.onError("Cannot connect")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if(response.isSuccessful) {
                    println("success")
                    onSearchOpponent()
                }
                else{
                    println("response failure")
                    iQueueLoadView.onError("Cannot connect")
                }
            }
        })

    }

    override fun onDropFromQueue() {
        val call = opponentApiService.cancelQueue(getApplicationToken())

        call.enqueue(object:Callback<List<String>>{
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("failure:"+t.message)
                iQueueLoadView.onError("Cannot connect")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if(response.isSuccessful){
                    println("successful")
                }
                else{
                    println("response failure")
                }
            }
        })


    }

}