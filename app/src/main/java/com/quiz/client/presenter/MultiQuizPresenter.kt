package com.quiz.client.presenter

import com.quiz.client.model.Score
import com.quiz.client.service.GameApiService
import com.quiz.client.view.IMultiQuizView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MultiQuizPresenter : IMultiQuizPresenter {

    val iMultiQuizView:IMultiQuizView
    val gameApiService: GameApiService

    constructor(iMultiQuizView: IMultiQuizView,gameApiService: GameApiService) {
        this.iMultiQuizView = iMultiQuizView
        this.gameApiService = gameApiService
    }

    override fun onupdateDeviceScoreInGame(game_code: String, serial: String, howmany: String) {

        val call = gameApiService.updateDeviceScoreInGame(game_code,serial,howmany)


        call.enqueue(object:Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                println("onupdateDeviceScoreInGame failure:"+t.message)
                iMultiQuizView.onError("onupdateDeviceScoreInGame Failure")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {

                if(response.isSuccessful){
                    iMultiQuizView.onScoreDeviceUpdateSuccess()
                }
                else{
                    println("onupdateDeviceScoreInGame response error:|CODE="+response.code()+"|MSG:"+response.body()+"|")
                    iMultiQuizView.onError("onupdateDeviceScoreInGame result not 200")
                }

            }
        })

    }

    override fun onUpdateDeviceFinishedAnswering(game_code: String, serial: String) {

        val call = gameApiService.updateDeviceFinishedAnsweringToQuestion(game_code,serial)


        call.enqueue(object:Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                println("onUpdateDeviceFinishedAnswering failure:"+t.message)
                iMultiQuizView.onError("onUpdateDeviceFinishedAnswering Failure")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    iMultiQuizView.onupdateDeviceFinishedAnsweringToQuestionSuccess()
                }
                else{
                    println("onUpdateDeviceFinishedAnswering response error:|CODE="+response.code()+"|MSG:"+response.body()+"|")
                    iMultiQuizView.onError("onUpdateDeviceFinishedAnswering result not 200")
                }
            }
        })

    }

    override fun onCheckAllDevicesAnswered(game_code: String) {

        val call = gameApiService.checkAllDevicesAnswered(game_code)

        call.enqueue(object:Callback<Boolean>{
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                println("onCheckAllDevicesAnswered failure:"+t.message)
                iMultiQuizView.onError("onCheckAllDevicesAnswered Failure")
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {

                if(response.isSuccessful && response.body()==true){
                    iMultiQuizView.oncheckAllDevicesAnsweredSuccess()
                }
                else if(response.isSuccessful && response.body()==false){
                    //wait
                }
                else{
                    println("onCheckAllDevicesAnswered response error:|CODE="+response.code()+"|MSG:"+response.body()+"|")
                    iMultiQuizView.onError("onCheckAllDevicesAnswered result not 200")
                }

            }
        })


    }

    override fun onFindScoreByUUID(game_code: String) {

        val call = gameApiService.findScoresByUUID(game_code)


        call.enqueue(object:Callback<List<Score>>{
            override fun onFailure(call: Call<List<Score>>, t: Throwable) {
                println("onFindScoreByUUID failure:"+t.message)
                iMultiQuizView.onError("onFindScoreByUUID Failure")
            }

            override fun onResponse(call: Call<List<Score>>, response: Response<List<Score>>) {
                if(response.isSuccessful){
                    iMultiQuizView.onfindScoresByUUID(response.body()!!.toList())
                }
                else{
                    println("onFindScoreByUUID response error:|CODE="+response.code()+"|MSG:"+response.body()+"|")
                    iMultiQuizView.onError("onFindScoreByUUID result not 200")
                }
            }
        })



    }
}