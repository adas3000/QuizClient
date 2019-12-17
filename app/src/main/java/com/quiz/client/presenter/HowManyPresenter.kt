package com.quiz.client.presenter

import com.quiz.client.service.QuizApiService
import com.quiz.client.view.IHowManyView

class HowManyPresenter : IHowManyPresenter {


    val iHowManyView: IHowManyView


    constructor(iHowManyView: IHowManyView) {
        this.iHowManyView = iHowManyView
    }


    override fun onHowMany(quizApiService: QuizApiService, category: String, value: Int) {


    }
}