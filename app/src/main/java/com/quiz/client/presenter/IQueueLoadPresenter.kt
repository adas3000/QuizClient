package com.quiz.client.presenter


interface IQueueLoadPresenter {
    fun onGoToQueue()
    fun onSearchOpponent()
    fun onDropFromQueue()
    fun onFindQuestionList(uuid:String)
}