package com.quiz.client.presenter

interface IPlayersCountPresenter {
    fun onCountSelect(category:String,questionCount:Int,value:Int)
    fun onJoinToQueue(string: String)
    fun onGoForQuestionList(uuid:String)
}