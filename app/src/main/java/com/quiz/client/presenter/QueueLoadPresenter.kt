package com.quiz.client.presenter

import com.quiz.client.service.OpponentApiService
import com.quiz.client.util.getApplicationToken
import com.quiz.client.view.IQueueLoadView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QueueLoadPresenter : IQueueLoadPresenter {

    val iQueueLoadView:IQueueLoadView

    constructor(iQueueLoadView:IQueueLoadView){
        this.iQueueLoadView = iQueueLoadView
    }


    override fun onSearchOpponent(opponentApiService: OpponentApiService) {




    }

    override fun onGoToQueue(opponentApiService: OpponentApiService) {

        val call = opponentApiService.goToQueue(getApplicationToken())

        call.enqueue(object: Callback<List<String>>{
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("failure:"+t.message)
                iQueueLoadView.onError("Cannot connect")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if(response.isSuccessful) {
                    println("success")
                    response.body()?.forEach{
                        println(it)
                    }
                    onSearchOpponent(opponentApiService)
                }
                else{
                    println("response failure:")
                    response.body()?.forEach {
                        println(it)
                    }
                    iQueueLoadView.onError("Cannot connect")
                }
            }
        })

    }
}