package com.quiz.client.presenter

import com.quiz.client.service.QuizApiService

interface IHowManyPresenter {
    fun onHowMany(quizApiService: QuizApiService,category:String,value:Int)
}