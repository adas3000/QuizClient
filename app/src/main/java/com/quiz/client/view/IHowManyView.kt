package com.quiz.client.view

import com.quiz.client.model.Question

interface IHowManyView {

    fun onHowManyResult(questionList:List<Question>)
    fun onErrorResult(message:String)

}