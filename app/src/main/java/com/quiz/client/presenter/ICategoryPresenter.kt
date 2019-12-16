package com.quiz.client.presenter

import com.quiz.client.service.QuizApiService

interface ICategoryPresenter {

    fun onCategory(quizApiService: QuizApiService)

}