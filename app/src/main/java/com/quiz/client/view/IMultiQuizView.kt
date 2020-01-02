package com.quiz.client.view

interface IMultiQuizView {
    fun onNextQuestion(correct:Boolean,time_remaining:Int)
}