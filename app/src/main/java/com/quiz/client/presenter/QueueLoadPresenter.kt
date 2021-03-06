package com.quiz.client.presenter

import com.quiz.client.component.DaggerAppComponent
import com.quiz.client.model.Question
import com.quiz.client.service.QueueApiService
import com.quiz.client.util.QuestionListKeeper
import com.quiz.client.util.getApplicationToken
import com.quiz.client.view.IQueueLoadView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class QueueLoadPresenter : IQueueLoadPresenter {

    val iQueueLoadView:IQueueLoadView
    val queueApiService: QueueApiService

    @Inject
    lateinit var nickName:String


    constructor(iQueueLoadView:IQueueLoadView, queueApiService: QueueApiService){
        this.iQueueLoadView = iQueueLoadView
        this.queueApiService = queueApiService

        val appComponent = DaggerAppComponent.builder().build()
        nickName = appComponent.provideNickName()
    }

    override fun onGoForQuestionList(uuid:String) {

        val call = queueApiService.findQuestionList(uuid)

        call.enqueue(object:Callback<List<Question>>{

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                println("failure:"+t.message)
                iQueueLoadView.onError("Cannot connect")
            }

            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {

                if(response.isSuccessful){
                    println("success")
                    QuestionListKeeper.questionListKeeper = response.body()!!
                    iQueueLoadView.onSuccess(uuid)
                }
                else{
                    println("response failure,MSG:"+response.message()+",CODE:"+response.code())
                    iQueueLoadView.onError("Question List error")
                }

            }

        })


    }

    override fun onSearchGame() {

        val call = queueApiService.findGameInQueue(getApplicationToken())

        call.enqueue(object:Callback<List<String>>{
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                iQueueLoadView.onError(t.message.toString())
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if(response.code()==200){
                    onGoForQuestionList(response.body()!![0])
                }
                else if(response.code()==202){
                    iQueueLoadView.onNoRoomsFounded()
                }
                else iQueueLoadView.onError(response.message()+"|CODE:"+response.code())
            }
        })



    }

    override fun onGoToQueue() {

        val call = queueApiService.joinToQueue(getApplicationToken(),nickName)

        call.enqueue(object: Callback<List<String>>{
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("failure:"+t.message)
                iQueueLoadView.onError("Cannot connect")
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if(response.isSuccessful) {
                    println("success")
                    onSearchGame()
                }
                else{
                    println("response failure")
                    iQueueLoadView.onError("Try again")
                }
            }
        })

    }

    override fun onDropFromQueue() {
        val call = queueApiService.cancelQueue(getApplicationToken())

        call.enqueue(object:Callback<List<String>>{
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("failure:"+t.message)
                iQueueLoadView.onCannotDropFromQueue()
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