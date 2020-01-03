package com.quiz.client.view

import java.io.Serializable

interface IMultiQuizParent : Serializable{
    fun onNextQuestion(correct:Boolean,time_remaining:Int)
}