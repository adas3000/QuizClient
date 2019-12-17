package com.quiz.client.presenter

import com.quiz.client.model.Question
import com.quiz.client.service.QuizApiService
import com.quiz.client.view.IHowManyView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HowManyPresenter : IHowManyPresenter {


    val iHowManyView: IHowManyView


    constructor(iHowManyView: IHowManyView) {
        this.iHowManyView = iHowManyView
    }


    override fun onHowMany(quizApiService: QuizApiService, category: String, value: Int) {

        val call = quizApiService.listQuestions(category, 1.toString())

        call.enqueue(object : Callback<List<Question>> {

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                iHowManyView.onErrorResult("Failure")
            }

            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                if (response.isSuccessful) {
                    iHowManyView.onHowManyResult(response.body()!!.toList())
                }
                else iHowManyView.onErrorResult("Failure")
            }
        })


    }
}