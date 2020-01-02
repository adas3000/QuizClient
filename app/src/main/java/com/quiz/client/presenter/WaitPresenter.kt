package com.quiz.client.presenter

import com.quiz.client.service.GameApiService
import com.quiz.client.view.IWaitView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WaitPresenter : IWaitPresenter {


    val iWaitView:IWaitView
    val gameApiService:GameApiService

    constructor(iWaitView:IWaitView,gameApiService:GameApiService){
        this.iWaitView = iWaitView
        this.gameApiService = gameApiService
    }

    override fun doCheckAllConnected(uuid: String) {

        val call = gameApiService.onAllPlayersConnected(uuid)
        val iWaitPresenter :IWaitPresenter= this

        call.enqueue(object: Callback<List<String>>{
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("docheckAllConnected,failure")
                iWaitView.onError("Internet Error")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {

                if(response.code()==100){
                    iWaitView.onContinue(uuid,iWaitPresenter)
                }
                else if(response.isSuccessful){
                    iWaitView.onSuccess(uuid)
                }
                else{
                    iWaitView.onError(response.message())
                }

            }
        })

    }


}