package com.quiz.client.presenter


interface IQueueLoadPresenter {
    fun onGoToQueue()
    fun onSearchGame()
    fun onDropFromQueue()
    fun onGoForQuestionList(uuid:String)
}